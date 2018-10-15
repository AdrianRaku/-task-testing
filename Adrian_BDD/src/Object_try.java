import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Object_try {
	private WebDriver wd;
	private String url;
	public static final String home_page = "http://automationpractice.com";
	public static final By email_Input = By.id("email");
	public static final By area_Input = By.id("message");
	public static final By submit_Input = By.id("submitMessage");
	public static final By combobox_Input = By.id("id_contact");
	public static final By file_Input = By.id("fileUpload");
	public static final By bestSellers_link = By.className("blockbestsellers");
	public static final By signIn_link = By.className("login");
	public static final By submitCreate = By.id("SubmitCreate");
	public static final By emailCreate = By.id("email_create");
}
