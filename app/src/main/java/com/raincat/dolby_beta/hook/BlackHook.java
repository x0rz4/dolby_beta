package com.raincat.dolby_beta.hook;

import android.content.Context;

import com.raincat.dolby_beta.helper.ExtraHelper;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * <pre>
 *     author : RainCat
 *     time   : 2019/10/26
 *     desc   : 黑胶，100黑胶，220音乐包
 *     version: 1.0
 * </pre>
 */

public class BlackHook {
    public BlackHook(Context context, int versionCode) {
        if (versionCode < 138) {
            XposedHelpers.findAndHookMethod(findClass("com.netease.cloudmusic.meta.Profile", context.getClassLoader()), "setVipType", int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if ((long) XposedHelpers.callMethod(param.thisObject, "getUserId") == Long.parseLong(ExtraHelper.getExtraDate(ExtraHelper.USER_ID)))
                        param.args[0] = 100;
                }
            });

            final String[] timeMethod = new String[]{"setVipProExpireTime", "setExpireTime"};
            for (String time : timeMethod) {
                XposedHelpers.findAndHookMethod(findClass("com.netease.cloudmusic.meta.Profile", context.getClassLoader()), time, long.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        if ((long) XposedHelpers.callMethod(param.thisObject, "getUserId") == Long.parseLong(ExtraHelper.getExtraDate(ExtraHelper.USER_ID)))
                            param.args[0] = System.currentTimeMillis() + 31536000000L;
                    }
                });
            }

            //主题
            findAndHookMethod(findClass("com.netease.cloudmusic.theme.core.ThemeInfo", context.getClassLoader()),
                    "i", XC_MethodReplacement.returnConstant(0));
            findAndHookMethod(findClass("com.netease.cloudmusic.theme.core.ThemeInfo", context.getClassLoader()),
                    "j", XC_MethodReplacement.returnConstant("免费"));
            findAndHookMethod(findClass("com.netease.cloudmusic.theme.core.ThemeInfo", context.getClassLoader()),
                    "o", XC_MethodReplacement.returnConstant(false));
            findAndHookMethod(findClass("com.netease.cloudmusic.theme.core.ThemeInfo", context.getClassLoader()),
                    "s", XC_MethodReplacement.returnConstant(false));
        } else {
            XposedHelpers.findAndHookMethod(findClass("com.netease.cloudmusic.meta.virtual.UserPrivilege", context.getClassLoader()), "setBlackVipType", int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if ((long) XposedHelpers.callMethod(param.thisObject, "getUserId") == Long.parseLong(ExtraHelper.getExtraDate(ExtraHelper.USER_ID)))
                        param.args[0] = 100;
                }
            });
            XposedHelpers.findAndHookMethod(findClass("com.netease.cloudmusic.meta.virtual.UserPrivilege", context.getClassLoader()), "setMusicPackageType", int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if ((long) XposedHelpers.callMethod(param.thisObject, "getUserId") == Long.parseLong(ExtraHelper.getExtraDate(ExtraHelper.USER_ID)))
                        param.args[0] = 220;
                }
            });
            XposedHelpers.findAndHookMethod(findClass("com.netease.cloudmusic.meta.virtual.UserPrivilege", context.getClassLoader()), "setRedVipAnnualCount", int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if ((long) XposedHelpers.callMethod(param.thisObject, "getUserId") == Long.parseLong(ExtraHelper.getExtraDate(ExtraHelper.USER_ID)))
                        param.args[0] = 100;
                }
            });

            final String[] timeMethod = new String[]{"setBlackVipExpireTime", "setMusicPackageExpireTime"};
            for (String time : timeMethod) {
                XposedHelpers.findAndHookMethod(findClass("com.netease.cloudmusic.meta.virtual.UserPrivilege", context.getClassLoader()), time, long.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        if ((long) XposedHelpers.callMethod(param.thisObject, "getUserId") == Long.parseLong(ExtraHelper.getExtraDate(ExtraHelper.USER_ID)))
                            param.args[0] = System.currentTimeMillis() + 31536000000L;
                    }
                });
            }

            //主题
            findAndHookMethod(findClass("com.netease.cloudmusic.theme.core.ThemeInfo", context.getClassLoader()),
                    "getPoints", XC_MethodReplacement.returnConstant(0));
            findAndHookMethod(findClass("com.netease.cloudmusic.theme.core.ThemeInfo", context.getClassLoader()),
                    "getPrice", XC_MethodReplacement.returnConstant("免费"));
            findAndHookMethod(findClass("com.netease.cloudmusic.theme.core.ThemeInfo", context.getClassLoader()),
                    "isVip", XC_MethodReplacement.returnConstant(false));
            findAndHookMethod(findClass("com.netease.cloudmusic.theme.core.ThemeInfo", context.getClassLoader()),
                    "isDigitalAlbum", XC_MethodReplacement.returnConstant(false));
        }

        //音质切换
        findAndHookMethod(findClass("com.netease.cloudmusic.meta.virtual.ResourcePrivilege", context.getClassLoader()),
                "isVipFee", XC_MethodReplacement.returnConstant(false));
        findAndHookMethod(findClass("com.netease.cloudmusic.meta.virtual.ResourcePrivilege", context.getClassLoader()),
                "getPlayMaxLevel", XC_MethodReplacement.returnConstant(999000));
        findAndHookMethod(findClass("com.netease.cloudmusic.meta.virtual.ResourcePrivilege", context.getClassLoader()),
                "getDownMaxLevel", XC_MethodReplacement.returnConstant(999000));
        findAndHookMethod(findClass("com.netease.cloudmusic.meta.virtual.ResourcePrivilege", context.getClassLoader()),
                "getFee", XC_MethodReplacement.returnConstant(0));
        findAndHookMethod(findClass("com.netease.cloudmusic.meta.virtual.ResourcePrivilege", context.getClassLoader()),
                "getPayed", XC_MethodReplacement.returnConstant(0));
        XposedBridge.hookAllMethods(findClass("com.netease.cloudmusic.meta.virtual.ResourcePrivilege", context.getClassLoader()),
                "isFee", XC_MethodReplacement.returnConstant(false));
        findAndHookMethod(findClass("com.netease.cloudmusic.meta.virtual.SongPrivilege", context.getClassLoader()),
                "canShare", XC_MethodReplacement.returnConstant(true));
        findAndHookMethod(findClass("com.netease.cloudmusic.meta.virtual.SongPrivilege", context.getClassLoader()),
                "getFreeLevel", XC_MethodReplacement.returnConstant(999000));
        findAndHookMethod(findClass("com.netease.cloudmusic.meta.virtual.ResourcePrivilege", context.getClassLoader()),
                "getFlag", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        //云盘歌曲&运算0x8不等于0
                        param.setResult(((int) param.getResult() & 0x8) == 0 ? 0 : param.getResult());
                    }
                });
    }
}
