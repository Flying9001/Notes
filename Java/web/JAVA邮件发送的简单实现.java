JAVA邮件发送的简单实现

JAVA MAIL是利用现有的邮件账户发送邮件的工具，比如说，我在网易注册一个邮箱账户，通过JAVA Mail的操控，我可以不亲自登录网易邮箱，让程序自动的使用网易邮箱发送邮件。这一机制被广泛的用在注册激活和垃圾邮件的发送等方面。

JavaMail可以到http://www.oracle.com/technetwork/java/javamail/index-138643.html进行下载,并将mail.jar添加到classpath即可。如果你使用的是JAVA EE SDK，则可以在C:\glassfishv3\glassfish\modules\mail.jar找到所需的jar包，同样需要添加的classpath。

JAVA邮件发送的大致过程是这样的的：

1、构建一个继承自javax.mail.Authenticator的具体类，并重写里面的getPasswordAuthentication()方法。此类是用作登录校验的，以确保你对该邮箱有发送邮件的权利。

2、构建一个properties文件，该文件中存放SMTP服务器地址等参数。

3、通过构建的properties文件和javax.mail.Authenticator具体类来创建一个javax.mail.Session。Session的创建，就相当于登录邮箱一样。剩下的自然就是新建邮件。

4、构建邮件内容，一般是javax.mail.internet.MimeMessage对象，并指定发送人，收信人，主题，内容等等。

5、使用javax.mail.Transport工具类发送邮件。

下面是我封装的代码，注释也比较详细。呼呼~~

1、首先是继承自javax.mail.Authenticator的一个具体类。getPasswordAuthentication()方法也就是构建一个PasswordAuthentication对象并返回，有点费解JAVA Mail这样的设计意图，可能是javax.mail.Authenticator为我们提供了附加的保证安全的验证措施吧。

 

package com.mzule.simplemail;
 
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
 
/**
 * 服务器邮箱登录验证
 * 
 * @author MZULE
 * 
 */
public class MailAuthenticator extends Authenticator {
 
    /**
     * 用户名（登录邮箱）
     */
    private String username;
    /**
     * 密码
     */
    private String password;
 
    /**
     * 初始化邮箱和密码
     * 
     * @param username 邮箱
     * @param password 密码
     */
    public MailAuthenticator(String username, String password) {
    this.username = username;
    this.password = password;
    }
 
    String getPassword() {
    return password;
    }
 
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(username, password);
    }
 
    String getUsername() {
    return username;
    }
 
    public void setPassword(String password) {
    this.password = password;
    }
 
    public void setUsername(String username) {
    this.username = username;
    }
 
}
 

2、邮件发送类，剩下的步骤都是在这个类实现的。代码中的SimpleMail是封装了邮件主题和内容的一个POJO。觉得在一个方法参数中既包含主题又包含内容，不太合适，故重载了此方法。还有就是因为大多数邮箱的SMTP服务器地址都是可以通过邮箱地址算出来，简单起见，提供了一个不需要SMTP服务器地址的构造器。

package com.mzule.simplemail;
 
import java.util.List;
import java.util.Properties;
 
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
 
/**
 * 简单邮件发送器，可单发，群发。
 * 
 * @author MZULE
 * 
 */
public class SimpleMailSender {
 
    /**
     * 发送邮件的props文件
     */
    private final transient Properties props = System.getProperties();
    /**
     * 邮件服务器登录验证
     */
    private transient MailAuthenticator authenticator;
 
    /**
     * 邮箱session
     */
    private transient Session session;
 
    /**
     * 初始化邮件发送器
     * 
     * @param smtpHostName
     *                SMTP邮件服务器地址
     * @param username
     *                发送邮件的用户名(地址)
     * @param password
     *                发送邮件的密码
     */
    public SimpleMailSender(final String smtpHostName, final String username,
        final String password) {
    init(username, password, smtpHostName);
    }
 
    /**
     * 初始化邮件发送器
     * 
     * @param username
     *                发送邮件的用户名(地址)，并以此解析SMTP服务器地址
     * @param password
     *                发送邮件的密码
     */
    public SimpleMailSender(final String username, final String password) {
    //通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
    final String smtpHostName = "smtp." + username.split("@")[1];
    init(username, password, smtpHostName);
 
    }
 
    /**
     * 初始化
     * 
     * @param username
     *                发送邮件的用户名(地址)
     * @param password
     *                密码
     * @param smtpHostName
     *                SMTP主机地址
     */
    private void init(String username, String password, String smtpHostName) {
    // 初始化props
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", smtpHostName);
    // 验证
    authenticator = new MailAuthenticator(username, password);
    // 创建session
    session = Session.getInstance(props, authenticator);
    }
 
    /**
     * 发送邮件
     * 
     * @param recipient
     *                收件人邮箱地址
     * @param subject
     *                邮件主题
     * @param content
     *                邮件内容
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(String recipient, String subject, Object content)
        throws AddressException, MessagingException {
    // 创建mime类型邮件
    final MimeMessage message = new MimeMessage(session);
    // 设置发信人
    message.setFrom(new InternetAddress(authenticator.getUsername()));
    // 设置收件人
    message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
    // 设置主题
    message.setSubject(subject);
    // 设置邮件内容
    message.setContent(content.toString(), "text/html;charset=utf-8");
    // 发送
    Transport.send(message);
    }
 
    /**
     * 群发邮件
     * 
     * @param recipients
     *                收件人们
     * @param subject
     *                主题
     * @param content
     *                内容
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(List<String> recipients, String subject, Object content)
        throws AddressException, MessagingException {
    // 创建mime类型邮件
    final MimeMessage message = new MimeMessage(session);
    // 设置发信人
    message.setFrom(new InternetAddress(authenticator.getUsername()));
    // 设置收件人们
    final int num = recipients.size();
    InternetAddress[] addresses = new InternetAddress[num];
    for (int i = 0; i < num; i++) {
        addresses[i] = new InternetAddress(recipients.get(i));
    }
    message.setRecipients(RecipientType.TO, addresses);
    // 设置主题
    message.setSubject(subject);
    // 设置邮件内容
    message.setContent(content.toString(), "text/html;charset=utf-8");
    // 发送
    Transport.send(message);
    }
 
    /**
     * 发送邮件
     * 
     * @param recipient
     *                收件人邮箱地址
     * @param mail
     *                邮件对象
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(String recipient, SimpleMail mail)
        throws AddressException, MessagingException {
    send(recipient, mail.getSubject(), mail.getContent());
    }
 
    /**
     * 群发邮件
     * 
     * @param recipients
     *                收件人们
     * @param mail
     *                邮件对象
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(List<String> recipients, SimpleMail mail)
        throws AddressException, MessagingException {
    send(recipients, mail.getSubject(), mail.getContent());
    }
 
}
3、调用上面的邮箱发送器，可以构建一个工厂类，工厂类可以封装创建的过程，所以通过读配置文件获取邮箱用户名，密码都会变得十分方便。下面的代码是我在写观察者模式的时候写的，只是简单演示了工厂类。

package com.mzule.dp.observer.factory;
 
import com.mzule.dp.observer.constant.MailSenderType;
import com.mzule.simplemail.SimpleMailSender;
 
/**
 * 发件箱工厂
 * 
 * @author MZULE
 * 
 */
public class MailSenderFactory {
 
    /**
     * 服务邮箱
     */
    private static SimpleMailSender serviceSms = null;
 
    /**
     * 获取邮箱
     * 
     * @param type 邮箱类型
     * @return 符合类型的邮箱
     */
    public static SimpleMailSender getSender(MailSenderType type) {
    if (type == MailSenderType.SERVICE) {
        if (serviceSms == null) {
        serviceSms = new SimpleMailSender("invisible@126.com",
            "hidden");
        }
        return serviceSms;
    }
    return null;
    }
 
}
4、发送邮件，还是观察者模式DEMO里面的代码，呼呼。

package com.mzule.dp.observer.observer;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
 
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
 
import com.mzule.dp.observer.constant.MailSenderType;
import com.mzule.dp.observer.factory.MailSenderFactory;
import com.mzule.dp.observer.po.Product;
import com.mzule.simplemail.SimpleMailSender;
 
public class ProductPriceObserver implements Observer {
 
    @Override
    public void update(Observable obj, Object arg) {
    Product product = null;
    if (obj instanceof Product) {
        product = (Product) obj;
    }
    if (arg instanceof Float) {
        Float price = (Float) arg;
        Float decrease = product.getPrice() - price;
        if (decrease > 0) {
        // 发送邮件
        SimpleMailSender sms = MailSenderFactory
            .getSender(MailSenderType.SERVICE);
        List<String> recipients = new ArrayList<String>();
        recipients.add("invisible@qq.com");
        recipients.add("invisible@gmail.com");
        try {
            for (String recipient : recipients) {
            sms.send(recipient, "价格变动", "您关注的物品"
                + product.getName() + "降价了，由"
                + product.getPrice() + "元降到" + price + "元，降幅达"
                + decrease + "元人民币。赶快购物吧。");
            }
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        }
    }
    }
 
}
5、剩下的就是去查看邮件是否发送成功了。呼呼~~