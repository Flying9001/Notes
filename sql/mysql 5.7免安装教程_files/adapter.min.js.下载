(function() {
    
    var modules = {};
    function isFunction(obj) {
        return Object.prototype.toString.call(obj) === "[object Function]";
    }
    function define(name, deps, factory) {
        if (modules[name]) {
            throw new Error("Module " + name + " has been defined already.");
        }
        if (isFunction(deps)) {
            factory = deps;
        }
        modules[name] = {
            factory: factory,
            inited: false,
            exports: null
        };
    }
    function run(name) {
        var module, exports, mod, ret;
        module = modules[name];
        exports = {};
        mod = {
            exports: {}
        };
        if (isFunction(module.factory)) {
            ret = module.factory.call(undefined, require, exports, mod);
            if (ret !== undefined) {
                module.exports = ret;
            } else {
                if (mod.hasOwnProperty("exports") && typeof mod.exports === "object" && mod.exports instanceof Object === true) {
                    var tag = false;
                    var k, v;
                    for (k in mod.exports) {
                        if (mod.exports.hasOwnProperty(k)) {
                            tag = true;
                        }
                    }
                    if (tag === false) {
                        module.exports = exports;
                    } else {
                        module.exports = mod.exports;
                    }
                } else {
                    module.exports = mod.exports;
                }
            }
        } else {
            throw new Error("Module " + name + " has no factory.");
        }
        module.inited = true;
    }
    function require(name) {
        var module;
        module = modules[name];
        if (!module) {
            throw new Error("Module " + name + " is not defined.");
        }
        if (module.inited === false) {
            run(name);
        }
        return module.exports;
    }
    define("/opt/jenkins/workspace/changyan-fe-frontend-v3/src/adapter.js", function(require, exports, module) {
        var $$util = require("/opt/jenkins/workspace/changyan-fe-frontend-v3/src/util/util.js");
        var fnGetBeConfig = function(base, appid, fn) {
            var cb = "changyan" + Math.floor(Math.random() * 1e3 * 1e3 * 1e3);
            var api = base + "api/2/config/get/" + appid + "?callback=" + cb;
            window[cb] = function(data) {
                data = $$util.UrlSwitchHttps(data);
                if (typeof fn === "function") {
                    fn(data);
                }
            };
            $$util.loadJs(api, function() {
                $$util.deleteProperty(window, cb);
            });
        };
        var fnUUID = function(api, cookie, fn) {
            var uuid = cookie.debug_uuid;
            if (typeof uuid === "string" && uuid.length === 32) {
                fn && fn();
                return;
            }
            var $$getUuid = require("/opt/jenkins/workspace/changyan-fe-frontend-v3/src/get-uuid.js");
            var debug_uuid = $$getUuid.getUUid();
            var now = new Date;
            var t = (new Date(now.setDate(now.getDate() + 365))).toString();
            var cookieInner = "debug_uuid=" + debug_uuid + "; expires=" + t + "; path=/; domain=.changyan.sohu.com";
            var cb = "changyan" + Math.floor(Math.random() * 1e3 * 1e3 * 1e3);
            var url = api + "debug/cookie?setCookie=" + cookieInner + "&callback=" + cb + "&" + new Date;
            window[cb] = function() {
                cookie.debug_uuid = debug_uuid;
                fn && fn();
            };
            $$util.loadJs(url, function() {
                $$util.deleteProperty(window, cb);
            });
        };
        var fnAdapter = function(config, cookie) {
            var feConfig = require("/opt/jenkins/workspace/changyan-fe-frontend-v3/src/fe-config.js");
            fnGetBeConfig(config.api, feConfig.appid, function(beConfig) {
                $$util.deleteProperty(config, "version");
                $$util.deleteProperty(feConfig, "conf");
                config.isvName = beConfig.data.isv.name;
                config.isvAuditMode = beConfig.data.isv.auditMode;
                config.isvStatus = beConfig.data.isv.status;
                config.isvUrl = beConfig.data.isv.url;
                config.isvLogoUrl = beConfig.data.isv.isvLogoUrl;
                if (beConfig.data.isv.icpFrozenDate !== undefined) {
                    config.icpFrozenDate = beConfig.data.isv.icpFrozenDate;
                }
                beConfig = beConfig.data && beConfig.data.main || {};
                if (beConfig.is_new_cdn === "true") {
                    config.res = config.res.replace("changyan.itc.cn", "a9720e8c2dac4.cdn.sohucs.com");
                }
                if (beConfig.allow_phoneuser === "1") {
                    if (beConfig.login_external_platform !== undefined) {
                        if (!beConfig.login_external_platform.match(/15/)) {
                            if (beConfig.login_external_platform !== "") {
                                beConfig.login_external_platform = beConfig.login_external_platform + ",15";
                            } else {
                                beConfig.login_external_platform = "15";
                            }
                        }
                    }
                    $$util.deleteProperty(beConfig, "allow_phoneuser");
                }
                if (feConfig.ismobile) {
                    var mobile_code_version = beConfig.mobile_code_version || "v2";
                    var liebao = [ "cyrIqbsmb", "cyruNjvn2" ];
                    var isNewVersion = beConfig.isNewVersion === true && liebao.indexOf(config.appid) >= 0;
                    var oldConfig = {};
                    var initData = function() {
                        feConfig.config = beConfig;
                        var mobile_isv_type = feConfig.config.mobile_isv_type, mobile_css_type = feConfig.config.mobile_css_type;
                        feConfig.uri = {
                            res: config.protocol + "changyan.itc.cn/upload/mobile/v2/",
                            api: config.protocol + "changyan.sohu.com/"
                        };
                        if (mobile_isv_type !== "stable" && typeof mobile_css_type !== "string") {
                            feConfig.config.mobile_css_type = "11";
                        }
                        oldConfig = feConfig;
                        window.cyan.getAdapterVersionModule = function() {
                            return {
                                get: function() {
                                    return oldConfig;
                                }
                            };
                        };
                    };
                    if (mobile_code_version === "v2") {
                        initData();
                        var fnGetVersion = function() {
                            var version = "v20170323854";
                            if (version.indexOf("##CY") >= 0) {
                                version = "v3-debug-v3";
                            }
                            return version;
                        };
                        oldConfig.version = fnGetVersion();
                        oldConfig.uri.res = config.protocol + "changyan.itc.cn/upload/mobile/v2/";
                        $$util.loadJs(oldConfig.uri.res + "wap-js/src/init-build.js?" + oldConfig.version);
                        return;
                    }
                    if (mobile_code_version === "v1") {
                        initData();
                        if (isNewVersion) {
                            oldConfig.uri.res = config.protocol + "changyan.itc.cn/upload/mobile/v2/";
                        } else {
                            oldConfig.uri.res = config.protocol + "changyan.itc.cn/upload/mobile/v1/";
                        }
                        $$util.loadJs(oldConfig.uri.res + "wap-js/src/init-build.js?" + oldConfig.version);
                        return;
                    }
                    if (mobile_code_version === "v0") {
                        initData();
                        if (isNewVersion) {
                            oldConfig.uri.res = config.protocol + "changyan.itc.cn/upload/mobile/v2/";
                        } else {
                            oldConfig.uri.res = config.protocol + "changyan.itc.cn/upload/mobile/";
                        }
                        $$util.loadJs(oldConfig.uri.res + "wap-js/src/init-build.js?" + oldConfig.version);
                        return;
                    }
                } else {
                    var key;
                    for (key in beConfig) {
                        if (beConfig.hasOwnProperty(key) && key.match(/^mobile/)) {
                            $$util.deleteProperty(beConfig, key);
                        }
                    }
                    if (beConfig.code_version !== "v3" && beConfig.code_version !== "v4") {
                        $$util.loadJs(config.protocol + "changyan.sohu.com/upload/version.js");
                        return;
                    }
                    if (!window.XMLHttpRequest) {
                        $$util.loadJs(config.protocol + "changyan.sohu.com/upload/version.js");
                        return;
                    }
                }
                if (cookie.debug_v3 === "true") {
                    require.async("./start.js", function(start) {
                        start(config, feConfig, beConfig, cookie);
                    });
                } else {
                    $$util.loadJs(config.res + "start.min.js", function() {
                        var start = window.changyan.api.getModules("start.js");
                        start(config, feConfig, beConfig, cookie);
                    });
                }
                $$util.deleteProperty(config, "debug");
            });
        };
        var fnInit = function(config, cookie) {
            fnUUID(config.api, cookie, function() {
                fnAdapter(config, cookie);
            });
        };
        module.exports = fnInit;
    });
    define("/opt/jenkins/workspace/changyan-fe-frontend-v3/src/util/util.js", function(require, exports, module) {
        exports.loadJs = function(src, fun) {
            var head = document.getElementsByTagName("head")[0] || document.head || document.documentElement;
            var script = document.createElement("script");
            script.setAttribute("type", "text/javascript");
            script.setAttribute("charset", "UTF-8");
            script.setAttribute("src", src);
            if (typeof fun === "function") {
                if (window.attachEvent) {
                    script.onreadystatechange = function() {
                        var r = script.readyState;
                        if (r === "loaded" || r === "complete") {
                            script.onreadystatechange = null;
                            fun();
                        }
                    };
                } else {
                    script.onload = fun;
                    script.onerror = fun;
                }
            }
            head.appendChild(script);
        };
        exports.trim = function() {
            var trimLeft = /^\s+/;
            var trimRight = /\s+$/;
            var trim = String.prototype.trim;
            return trim ? function(text) {
                return text === null ? "" : trim.call(text);
            } : function(text) {
                return text === null ? "" : text.toString().replace(trimLeft, "").replace(trimRight, "");
            };
        }();
        exports.upper = function(txt) {
            if (typeof txt !== "string") {
                return "";
            }
            var reg = /^[a-z]$/;
            var upStr = "";
            for (var i = 0; i < txt.length; i++) {
                if (reg.test(txt.charAt(i))) {
                    upStr += String.fromCharCode(txt.charCodeAt(i) - 32);
                } else {
                    upStr += txt.charAt(i);
                }
            }
            return upStr;
        };
        exports.deleteProperty = function(obj, key) {
            if (typeof obj !== "object") {
                return;
            }
            try {
                delete obj[key];
            } catch (e) {
                obj[key] = undefined;
            }
        };
        exports.queryObject = function(obj) {
            if (typeof obj !== "object") {
                return;
            }
            var str = "", key;
            for (key in obj) {
                if (str === "") {
                    str += key + "=" + window.encodeURIComponent(obj[key]);
                } else {
                    str += "&" + key + "=" + window.encodeURIComponent(obj[key]);
                }
            }
            return str;
        };
        exports.msieVersion = function() {
            var ua = window.navigator.userAgent;
            var msie = ua.indexOf("MSIE ");
            if (msie > 0) {
                return parseInt(ua.substring(msie + 5, ua.indexOf(".", msie)));
            } else {
                return false;
            }
            return false;
        };
        exports.UrlSwitchHttps = function(data) {
            if (!window.location.href.match(/^https:\/\//)) {
                return data;
            }
            if (!data) return data;
            var str;
            var flag = false;
            if (typeof data === "object") {
                str = JSON.stringify(data);
                flag = true;
            } else if (typeof data === "string") {
                str = data;
            } else {
                return data;
            }
            str = str.replace(/=http:\/\//g, "[[[]]]");
            str = str.replace(/http:\/\//g, "//");
            str = str.replace(/\[\[\[\]\]\]/g, "=http://");
            if (flag) {
                return JSON.parse(str);
            }
            return str;
        };
    });
    define("/opt/jenkins/workspace/changyan-fe-frontend-v3/src/get-uuid.js", function(require, exports, module) {
        exports.getUUid = function() {
            function UUID() {
                this.id = this.createUUID();
            }
            UUID.prototype.valueOf = function() {
                return this.id;
            };
            UUID.prototype.toString = function() {
                return this.id;
            };
            UUID.prototype.createUUID = function() {
                var dg = new Date(1582, 10, 15, 0, 0, 0, 0);
                var dc = new Date;
                var t = dc.getTime() - dg.getTime();
                var tl = UUID.getIntegerBits(t, 0, 31);
                var tm = UUID.getIntegerBits(t, 32, 47);
                var thv = UUID.getIntegerBits(t, 48, 59) + "1";
                var csar = UUID.getIntegerBits(UUID.rand(4095), 0, 7);
                var csl = UUID.getIntegerBits(UUID.rand(4095), 0, 7);
                var n = UUID.getIntegerBits(UUID.rand(8191), 0, 7) + UUID.getIntegerBits(UUID.rand(8191), 8, 15) + UUID.getIntegerBits(UUID.rand(8191), 0, 7) + UUID.getIntegerBits(UUID.rand(8191), 8, 15) + UUID.getIntegerBits(UUID.rand(8191), 0, 15);
                return tl + tm + thv + csar + csl + n;
            };
            UUID.getIntegerBits = function(val, start, end) {
                var base16 = UUID.returnBase(val, 16);
                var quadArray = new Array;
                var quadString = "";
                var i = 0;
                for (i = 0; i < base16.length; i++) {
                    quadArray.push(base16.substring(i, i + 1));
                }
                for (i = Math.floor(start / 4); i <= Math.floor(end / 4); i++) {
                    if (!quadArray[i] || quadArray[i] == "") quadString += "0"; else quadString += quadArray[i];
                }
                return quadString;
            };
            UUID.returnBase = function(number, base) {
                return number.toString(base).toUpperCase();
            };
            UUID.rand = function(max) {
                return Math.floor(Math.random() * (max + 1));
            };
            var ret = UUID.prototype.createUUID();
            return ret;
        };
    });
    define("/opt/jenkins/workspace/changyan-fe-frontend-v3/src/fe-config.js", function(require, exports, module) {
        var isv = {
            _config: {},
            _changyanApiConfig: {},
            _readyHandles: [],
            _script: {},
            _url: undefined,
            _title: undefined,
            _sid: undefined,
            _useIframe: undefined,
            _ismobile: undefined
        };
        (function() {
            (function() {
                var getStartScript = function() {
                    var scripts = document.getElementsByTagName("script"), i, currentScript;
                    for (i = 0; i < scripts.length; i++) {
                        if (scripts[i].id === "changyan_mobile_js") {
                            isv._ismobile = true;
                        }
                        if (/changyan\.js/gi.test(scripts[i].src) || /changyan-plus\.js/gi.test(scripts[i].src) || scripts[i].id === "changyan_mobile_js") {
                            currentScript = scripts[i];
                            break;
                        }
                    }
                    return currentScript;
                };
                var getUrlParams = function(url) {
                    var obj = {};
                    if (!url || typeof url !== "string") {
                        return obj;
                    }
                    var paramStr = url.split("?")[1] || "";
                    var paramArr = paramStr.split("&");
                    var i, subArr;
                    if (paramArr.length) {
                        for (i = 0; i < paramArr.length; i++) {
                            subArr = paramArr[i].split("=");
                            if (!obj[subArr[0]]) {
                                obj[subArr[0]] = subArr[1];
                            }
                        }
                    }
                    return obj;
                };
                var script = getStartScript();
                var url = script && script.src || "";
                var params = getUrlParams(url);
                isv._script.appid = params.appid || params.client_id;
                isv._script.conf = params.conf;
            })();
            if (typeof window._config === "object") {
                isv._config = window._config || {};
            }
            if (window.changyan && window.changyan.api && window.changyan.api.tmpIsvPageConfig) {
                isv._changyanApiConfig = window.changyan.api.tmpIsvPageConfig || {};
            }
            if (window.changyan && window.changyan.api && window.changyan.api.tmpHandles) {
                isv._readyHandles = window.changyan.api.tmpHandles || [];
            }
            if (window.cyan && window.cyan.api && window.cyan.api.tmpHandles) {
                isv._readyHandles = window.cyan.api.tmpHandles || [];
            }
            (function() {
                if (window.SCS_NO_IFRAME === true) {
                    isv._useIframe = false;
                } else {
                    if (window.changyan && window.changyan.api && window.changyan.api.tmpIsvPageConfig) {
                        isv._useIframe = false;
                    } else {
                        isv._useIframe = true;
                    }
                }
            })();
            (function() {
                var el = window.document.getElementById("SOHUCS");
                try {
                    isv._sid = el.getAttribute("sid");
                } catch (e) {}
            })();
            isv._url = window.location.href;
            if (isv._ismobile === true && document.querySelector("link[rel=canonical]")) {
                isv._url = document.querySelector("link[rel=canonical]").getAttribute("href") || location.href;
            }
            isv._title = window.document.title;
            (function() {
                var $$util = require("/opt/jenkins/workspace/changyan-fe-frontend-v3/src/util/util.js");
                if (isv._script.appid !== "cyruNjvn2" && isv._ismobile === true) {
                    $$util.deleteProperty(window.cyan.api, "tmpHandles");
                }
            })();
        })();
        (function() {
            if (isv._config.appid === undefined && isv._script.appid === undefined) {
                isv._config.appid = "error-appid";
            }
            if (isv._config.appid === undefined && isv._script.appid !== undefined) {
                isv._config.appid = isv._script.appid;
            }
            if (isv._config.url === undefined && isv._url === undefined) {
                isv._config.url = "error-url";
            }
            if (isv._config.url === undefined && isv._url !== undefined) {
                isv._config.url = isv._url;
            }
            if (isv._config.title === undefined && isv._title === undefined) {
                isv._config.title = "";
            }
            if (isv._config.title === undefined && isv._title !== undefined) {
                isv._config.title = isv._title;
            }
            if (isv._config.sid === undefined && isv._sid !== undefined) {
                isv._config.sid = isv._sid;
            }
            var key, val;
            for (key in isv._config) {
                if (isv._config.hasOwnProperty(key) && !isv._changyanApiConfig.hasOwnProperty(key)) {
                    isv._changyanApiConfig[key] = isv._config[key];
                }
            }
            isv._changyanApiConfig.ismobile = isv._ismobile;
            isv._changyanApiConfig.readyHandles = isv._readyHandles || [];
        })();
        module.exports = isv._changyanApiConfig;
    });
    run("/opt/jenkins/workspace/changyan-fe-frontend-v3/src/adapter.js");
        window.changyan.api.getAdapterModules = function (module) {            var key = "/opt/jenkins/workspace/changyan-fe-frontend-v3/src/" + module;            return require(key);        };        }());    