(function () {
    var loadJs = function (src, fun) {
        var head = document.getElementsByTagName('head')[0] || document.head || document.documentElement;

        var script = document.createElement('script');
        script.setAttribute('type', 'text/javascript');
        script.setAttribute('charset', 'UTF-8');
        script.setAttribute('src', src);

        if (typeof fun === 'function') {
            if (window.attachEvent) {
                script.onreadystatechange = function () {
                    var r = script.readyState;
                    if (r === 'loaded' || r === 'complete') {
                        script.onreadystatechange = null;
                        fun();
                    }
                };
            } else {
                script.onload = fun;
            }
        }
        head.appendChild(script);
    };


    var fnGetVersion = function () {
        var version = 'v20170323854';
        if (version.indexOf('##CY') >= 0) {
            version = 'v3-debug-v3';
        }

        return version;
    };


    var fnGetCookie = function (fn) {
        var cb = 'changyan' + Math.floor(Math.random() * 1000 * 1000 * 1000);
        var protocol = (('https:' == window.document.location.protocol) ? "https://" : "http://");
        var api = protocol + 'changyan.sohu.com/debug/cookie?callback=' + cb;

        window[cb] = function (data) {
            var cookie = data && data.cookie || '';
            cookie = cookie.split(';');

            var i, v;
            var map = {};
            for (i = 0; i < cookie.length; i++) {
                v = cookie[i];
                v = v.split('=');
                v[0] = v[0] || '';
                v[1] = v[1] || '';
                v[0] = v[0].replace(/^\s/, '').replace(/\s$/,'');
                v[1] = v[1].replace(/^\s/, '').replace(/\s$/,'');
                if (v[0] !== '') {
                    map[v[0]] = v[1];
                }
            }
            if (typeof fn === 'function') {
                fn(map);
            }
        };

        loadJs(api, function () {
            try {
                delete window.cb;
            } catch (e) {
                window[cb] = undefined;
            }
        });
    };


    var fnInit = function () {
        var config = {};
        config.version = fnGetVersion();
        config.protocol = (('https:' == window.document.location.protocol) ? "https://" : "http://");
        config.res = config.protocol + 'changyan.itc.cn/v3/' + config.version + '/src/';
        config.base = config.protocol + 'changyan.itc.cn/';
        config.api = config.protocol + 'changyan.sohu.com/';

        if (config.protocol === 'https://') {
            config.res = config.protocol + 'changyan.itc.cn/v3/' + config.version + '/src/';
            config.base = config.protocol + 'changyan.itc.cn/';
        }

        fnGetCookie(function (cookie) {
            if (cookie.debug_v3 === 'true') {
                loadJs(config.res + 'lib/sea.v1.2.0.js', function () {
                    seajs.use(config.res + '/adapter.js', function (fn) {
                        fn && fn(config, cookie);
                    });
                });
            } else {
                loadJs(config.res + 'adapter.min.js', function () {
                    var adapter = window.changyan.api.getAdapterModules('adapter.js');
                    adapter(config, cookie);
                });
            }
        });
    };


    fnInit();
}());
