package tests.smoketest;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateAccountPage;
import pages.SignInPage;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class AccountCreationPageTest {

    CreateAccountPage createAccountPage;
    @Test
    public void accountCreationPageTest(){
        createAccountPage=new CreateAccountPage();

        Driver.getDriver().get(ConfigurationReader.getProperty("amazon_url"));

        ReusableMethods.hover(createAccountPage.signInTab);

        createAccountPage.startHereText.click();
        createAccountPage.nameBox.sendKeys(Faker.instance().name().fullName());
        createAccountPage.emailBox.sendKeys(Faker.instance().internet().emailAddress());
        String pass = Faker.instance().internet().password().toString();
        createAccountPage.passwordBox.sendKeys(pass);
        createAccountPage.passwordCheck.sendKeys(pass);

        createAccountPage.continueButton.click();
        Assert.assertTrue(createAccountPage.passwordAlertText.isDisplayed());

        createAccountPage.signInText.click();
        Assert.assertEquals(createAccountPage.signInPageAssertion.getText(),"Sign-In");

        Driver.getDriver().navigate().back();

        createAccountPage.businessAccountCreation.click();
        Assert.assertEquals(createAccountPage.businessAccountCreationText.getText(),"Let’s create your free Amazon Business account");
    }
}
