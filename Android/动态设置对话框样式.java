private View createContentView() {
        // 获取程序信息
        ResourceUtil resourceUtil = new ResourceUtil(mContext);
        ApkInfo apkInfo = resourceUtil.getApkInfo();

        // 设置根视图，即最外层的布局，确定最外层的边框风格。
        LinearLayout layout = new LinearLayout(mContext);
        layout.setBackgroundResource(R.drawable.about_dialog_bg);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layout.setLayoutParams(layoutParams);
        layout.setGravity(Gravity.CENTER);
        layout.setOrientation(LinearLayout.VERTICAL);
        final int layoutPad = 15;
        layout.setPadding(layoutPad, layoutPad, layoutPad, layoutPad);

        // 内层LinearLayout的布局，该布局包含了显示的主要文字及图片内容。
        LinearLayout inLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams inLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        inLayout.setLayoutParams(inLayoutParams);
        inLayout.setOrientation(LinearLayout.VERTICAL);
        inLayout.setGravity(Gravity.CENTER);
        final int inLayoutPad = 30;
        inLayout.setPadding(inLayoutPad, inLayoutPad, inLayoutPad, inLayoutPad);
        inLayout.setBackgroundResource(R.drawable.about_dialog_stroke);

        // 程序图标、程序名字、开发者网站的布局参数
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 10;

        // 程序图标
        ImageView icon = new ImageView(mContext);
        icon.setLayoutParams(params);
        icon.setImageResource(apkInfo.iconId);

        // 程序名字及版本号
        TextView progressName = new TextView(mContext);
        progressName.setLayoutParams(params);
        progressName.setTextAppearance(mContext,
                android.R.style.TextAppearance_Small);
        progressName.setTextColor(android.R.color.black);
        progressName.setText(apkInfo.programName + "  版本: V"
                + apkInfo.versionName);

        // 开发团队LOGO
        ImageView logo = new ImageView(mContext);
        logo.setLayoutParams(new LayoutParams(284, 208));
        logo.setImageResource(R.drawable.logo);

        // 开发者网站
        TextView authorBlog = new TextView(mContext);
        authorBlog.setTextAppearance(mContext,
                android.R.style.TextAppearance_Small);
        authorBlog.setTextColor(android.R.color.black);
        authorBlog.setText(R.string.author_blog);
        authorBlog.setGravity(Gravity.CENTER_HORIZONTAL);
        authorBlog.setAutoLinkMask(Linkify.WEB_URLS);
        authorBlog.setLayoutParams(params);

        inLayout.addView(icon);
        inLayout.addView(progressName);
        inLayout.addView(logo);
        inLayout.addView(authorBlog);

        layout.addView(inLayout);
        return layout;
    }