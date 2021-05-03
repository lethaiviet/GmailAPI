[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]



<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/lethaiviet/GmailAPI">
    <img src="https://ssl.gstatic.com/ui/v1/icons/mail/rfr/logo_gmail_lockup_default_1x_r2.png" alt="Logo">
  </a>

<h3 align="center">Gmail API demo with java</h3>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#Issues">Common issues</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->

## About The Project

This is only a small demo about how to use Gmail api:

* Send a message with the body is plain text or html
* Send a message with attachment
* Get the emails with query
* Get the link directly from the content email

### Folder Structure

<details><summary>CLICK ME</summary>

- __https://github.com/lethaiviet/SeleniumLvl2__
    - [README.md](README.md)
    - [build.gradle](build.gradle)
    - __cmd__
        - [00\_SetupAllureSever.bat](cmd/00_SetupAllureSever.bat)
        - [01\_RunSuite.bat](cmd/01_RunSuite.bat)
        - [02\_OpenAllureServer.bat](cmd/02_OpenAllureServer.bat)
        - [03\_OpenExtentReport.bat](cmd/03_OpenExtentReport.bat)
        - [Env.bat](cmd/Env.bat)
    - __gradle__
        - __wrapper__
            - [gradle\-wrapper.jar](gradle/wrapper/gradle-wrapper.jar)
            - [gradle\-wrapper.properties](gradle/wrapper/gradle-wrapper.properties)
    - [gradlew](gradlew)
    - [gradlew.bat](gradlew.bat)
    - __images__
        - [allure\_report.png](images/allure_report.png)
        - [extent\_report.png](images/extent_report.png)
        - [logo.png](images/logo.png)
        - [screenshot.png](images/screenshot.png)
    - [list.md](list.md)
    - [settings.gradle](settings.gradle)
    - __src__
        - __main__
            - __java__
                - __com__
                    - __railway__
                        - __commons__
                            - [Constants.java](src/main/java/com/railway/commons/Constants.java)
                            - __enums__
                                - [Dropdown.java](src/main/java/com/railway/commons/enums/Dropdown.java)
                                - [Location.java](src/main/java/com/railway/commons/enums/Location.java)
                                - [NavBar.java](src/main/java/com/railway/commons/enums/NavBar.java)
                                - [PageURL.java](src/main/java/com/railway/commons/enums/PageURL.java)
                        - __controls__
                            - __base__
                                - [IBaseControl.java](src/main/java/com/railway/controls/base/IBaseControl.java)
                                - [IClickable.java](src/main/java/com/railway/controls/base/IClickable.java)
                                - [IEditable.java](src/main/java/com/railway/controls/base/IEditable.java)
                                - __imp__
                                    - [Action.java](src/main/java/com/railway/controls/base/imp/Action.java)
                                    - [BaseControl.java](src/main/java/com/railway/controls/base/imp/BaseControl.java)
                                    - [Clickable.java](src/main/java/com/railway/controls/base/imp/Clickable.java)
                                    - [Editable.java](src/main/java/com/railway/controls/base/imp/Editable.java)
                            - __common__
                                - [IButton.java](src/main/java/com/railway/controls/common/IButton.java)
                                - [IComboBox.java](src/main/java/com/railway/controls/common/IComboBox.java)
                                - [IElement.java](src/main/java/com/railway/controls/common/IElement.java)
                                - [ILabel.java](src/main/java/com/railway/controls/common/ILabel.java)
                                - [ITextBox.java](src/main/java/com/railway/controls/common/ITextBox.java)
                                - __imp__
                                    - [Button.java](src/main/java/com/railway/controls/common/imp/Button.java)
                                    - [ComboBox.java](src/main/java/com/railway/controls/common/imp/ComboBox.java)
                                    - [Element.java](src/main/java/com/railway/controls/common/imp/Element.java)
                                    - [Label.java](src/main/java/com/railway/controls/common/imp/Label.java)
                                    - [TextBox.java](src/main/java/com/railway/controls/common/imp/TextBox.java)
                        - __data__
                            - [TicketInfo.java](src/main/java/com/railway/data/TicketInfo.java)
                            - [UserInfo.java](src/main/java/com/railway/data/UserInfo.java)
                        - __drivers__
                            - [ChromeDriver.java](src/main/java/com/railway/drivers/ChromeDriver.java)
                            - [DriverFactory.java](src/main/java/com/railway/drivers/DriverFactory.java)
                            - [DriverManager.java](src/main/java/com/railway/drivers/DriverManager.java)
                            - [FirefoxDriver.java](src/main/java/com/railway/drivers/FirefoxDriver.java)
                        - __pages__
                            - [BasePage.java](src/main/java/com/railway/pages/BasePage.java)
                            - [BookTicketPage.java](src/main/java/com/railway/pages/BookTicketPage.java)
                            - [HomePage.java](src/main/java/com/railway/pages/HomePage.java)
                            - [LoginPage.java](src/main/java/com/railway/pages/LoginPage.java)
                            - [MyTicketPage.java](src/main/java/com/railway/pages/MyTicketPage.java)
                            - [RegisterPage.java](src/main/java/com/railway/pages/RegisterPage.java)
                        - __utils__
                            - [CacheHelper.java](src/main/java/com/railway/utils/CacheHelper.java)
                            - [DriverUtils.java](src/main/java/com/railway/utils/DriverUtils.java)
                            - [JsonHelper.java](src/main/java/com/railway/utils/JsonHelper.java)
                            - [JsonLocatorUtils.java](src/main/java/com/railway/utils/JsonLocatorUtils.java)
                            - [LogUtils.java](src/main/java/com/railway/utils/LogUtils.java)
                            - [ScraperHelper.java](src/main/java/com/railway/utils/ScraperHelper.java)
                            - [StringHelper.java](src/main/java/com/railway/utils/StringHelper.java)
                            - [UserInfoUtils.java](src/main/java/com/railway/utils/UserInfoUtils.java)
        - __test__
            - __java__
                - __com__
                    - __railway__
                        - __definitions__
                            - [BookTicketDefinition.java](src/test/java/com/railway/definitions/BookTicketDefinition.java)
                            - [CommonDefinition.java](src/test/java/com/railway/definitions/CommonDefinition.java)
                            - [Hook.java](src/test/java/com/railway/definitions/Hook.java)
                            - [LoginDefinition.java](src/test/java/com/railway/definitions/LoginDefinition.java)
                            - [MyTicketDefinition.java](src/test/java/com/railway/definitions/MyTicketDefinition.java)
                            - [RegisterDefinition.java](src/test/java/com/railway/definitions/RegisterDefinition.java)
                        - __test__
                            - [BookTicketValidation.java](src/test/java/com/railway/test/BookTicketValidation.java)
                            - [CommonValidation.java](src/test/java/com/railway/test/CommonValidation.java)
                            - [LoginValidation.java](src/test/java/com/railway/test/LoginValidation.java)
                            - [MyTicketValidation.java](src/test/java/com/railway/test/MyTicketValidation.java)
                            - [RegisterValidation.java](src/test/java/com/railway/test/RegisterValidation.java)
            - __resources__
                - [allure.properties](src/test/resources/allure.properties)
                - __data__
                    - __data\_excel__
                        - [user\_info.xlsx](src/test/resources/data/data_excel/user_info.xlsx)
                    - __data\_json__
                        - [user\_info.json](src/test/resources/data/data_json/user_info.json)
                - [extent.properties](src/test/resources/extent.properties)
                - __features__
                    - [book\_ticket\_validation.feature](src/test/resources/features/book_ticket_validation.feature)
                    - [login\_validation.feature](src/test/resources/features/login_validation.feature)
                    - [my\_ticket\_validation.feature](src/test/resources/features/my_ticket_validation.feature)
                    - [register\_validation.feature](src/test/resources/features/register_validation.feature)
                - __locators__
                    - [locators.json](src/test/resources/locators/locators.json)
                - [log4j.properties](src/test/resources/log4j.properties)
                - __suites__
                    - [RegressionTesting.xml](src/test/resources/suites/RegressionTesting.xml)
    - __tool__
        - __allure\-commandline\-2.13.9__
        - __converterTool__
            - [ConverterExcelAndJson.exe](tool/converterTool/ConverterExcelAndJson.exe)

</details>

<!-- GETTING STARTED -->

## Getting Started

### Prerequisites

* [Git](https://git-scm.com/downloads)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)
* [Lombok Plugin on IntelliJ IDEA](https://projectlombok.org/setup/intellij)
* [An account gmail](https://accounts.google.com/SignUp?hl=en)

### Installation

Clone the repo

   ```sh
   git clone https://github.com/lethaiviet/GmailAPI.git
   ```

Create `credentials.json` for your account gmail

[comment]: <> (https://www.tablesgenerator.com/html_tables#)
<details><summary>More details</summary>
<table class="tg">
<tbody>
  <tr>
    <td class="tg-0pky" style="width: 20%;">Description</td>
    <td class="tg-0pky" style="width: 30%;">Steps</td>
    <td class="tg-0pky" style="width: 50%;">Image</td>
  </tr>
  <tr>
    <td class="tg-0pky" rowspan="3">In order to use Google APIs, we must create first the project on Google developer console</td>
    <td class="tg-0pky">1.Open <a href="https://console.cloud.google.com/">the Google Cloud Console</a></td>
    <td class="tg-0pky"><img src="doc/images/01_open_cloud_console.png" alt="open_cloud_console"></td>
  </tr>
  <tr>
    <td class="tg-0pky">2.Click to "CREATE PROJECT" > fill all information as the image</td>
    <td class="tg-0pky"><img src="doc/images/02_create_new_project.png" alt="create_new_project"></td>
  </tr>
  <tr>
    <td class="tg-0pky">3.Click to the CREATE button > we have the results as the image</td>
    <td class="tg-0pky"><img src="doc/images/03_results_after_creating_new_project.PNG" alt="results_after_creating_new_project"></td>
  </tr>

  <tr>
    <td class="tg-0pky" rowspan="4">When you use OAuth 2.0 for authorization, your app requests authorizations for one or more scopes of access from a Google Account. Google displays a consent screen to the user including a summary of your project and its policies and the requested scopes of access. You must configure the consent screen for all apps</td>
    <td class="tg-0pky">4.Click APIs & Services > Credentials</td>
    <td class="tg-0pky"><img src="doc/images/04_step_before_going_to_credential_page.png" alt="step_before_going_to_credential_page"></td>
  </tr>
  <tr>
    <td class="tg-0pky">5.Click "OAuth consent screen"</td>
    <td class="tg-0pky"><img src="doc/images/04_go_to_credential_page.png" alt="go_to_credential_page"></td>
  </tr>
  <tr>
    <td class="tg-0pky">6.Click the user type for your app > select "External"</td>
    <td class="tg-0pky"><img src="doc/images/05_select_user_type.png" alt="select_user_type"></td>
  </tr>
  <tr>
    <td class="tg-0pky">7.Click Create > fill out the form</td>
    <td class="tg-0pky"><img src="doc/images/06_fill_app_info_form.png" alt="06_fill_app_info_form"><br>
<img src="doc/images/06_fill_app_info_form_scope.png" alt="06_fill_app_info_form_scope"><br>
<img src="doc/images/06_fill_app_info_form_test_users.png" alt="06_fill_app_info_form_test_users"><br>
</td>
  </tr>

  <tr>
    <td class="tg-0pky" rowspan="3">In order to access any data, we need to create credentials. These credentials identify our project to google and are used by our application to authorize and authenticate a user via Oauth2</td>
    <td class="tg-0pky">8.Click to "Credentials" > Click to "Create Credentials"</td>
    <td class="tg-0pky"><img src="doc/images/07_step_before_creating_oauth_client_id.png" alt="07_step_before_creating_oauth_client_id"></td>
  </tr>
  <tr>
    <td class="tg-0pky">9.Fill out the form as the image</td>
    <td class="tg-0pky"><img src="doc/images/07_create_oauth_client_id.png" alt="07_create_oauth_client_id"></td>
  </tr>
  <tr>
    <td class="tg-0pky">10.Click to the CREATE button > we have the results as the image</td>
    <td class="tg-0pky"><img src="doc/images/08_results_after_creating_oauth_client_id.PNG" alt="08_results_after_creating_oauth_client_id"></td>
  </tr>

  <tr>
    <td class="tg-0pky" rowspan="1">In order to use gmail api, we need enable it</td>
    <td class="tg-0pky">8.Click to "Library" > Search "Gmail api" > click to "Enable"</td>
    <td class="tg-0pky"><img src="doc/images/10_enable_gmail_api_in_library.png" alt="10_enable_gmail_api_in_library"></td>
  </tr>
</tbody>
</table>
</details>
<!-- USAGE EXAMPLES -->

## Usage

1. Setup allure path to environment variable
   ```sh
   cmd\00_SetupAllureSever.bat
   ```
2. Run suite
   ```sh
   cmd\01_RunSuite.bat
   ```
3. Generate report and open allure server
   ```sh
   cmd\02_OpenAllureServer.bat
   ```
   [![allure-report][allure-report]](https://github.com/lethaiviet/SeleniumLvl2/blob/master/images/allure_report.png)
4. Or Open extent report
   ```sh
   cmd\03_OpenExtentReport.bat
   ```
   [![extent-report][extent-report]](https://github.com/lethaiviet/SeleniumLvl2/blob/master/images/extent_report.png)

<!-- CONTACT -->

## Contact

Le Thai Viet - [lethaiviet92@gmail.com]()

Project Link: [https://github.com/lethaiviet/SeleniumLvl2](https://github.com/lethaiviet/SeleniumLvl2)



<!-- ISSUES -->

## Issues

| Issue | How to fix |
| ------------- | ------------- |
| The report is generated **
allure-testng** CANNOT show all the steps in cucumber  | Work around: <ul><li>Implement `cucumber.junit` with plugin `io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm` </li><li> Use extent report while waiting for allure-testng is fixed</li></ul> Note: <ul><li> [Allure Test NG report TestBody is missing](https://stackoverflow.com/questions/57566093/allure-test-ng-report-testbody-is-missing) </li><li>[Test body is missed in report if run test via "java org.testng.TestNG suite.xml"](https://github.com/allure-framework/allure-java/issues/301) </li> <li>[Test body is missing in (cucumber) testNG report"](https://github.com/allure-framework/allure-java/issues/398) </li></ul> |
| Build project on IntelliJ IDEA with the below error: <br> `cannot find symbol` <br> `int numTicketBooking = ticketInfoLst.get(0).getAmount();` | [Install Lombok Plugin on IntelliJ IDEA](https://projectlombok.org/setup/intellij)  |

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/lethaiviet/GmailAPI.svg?style=for-the-badge

[contributors-url]: https://github.com/lethaiviet/GmailAPI/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/lethaiviet/GmailAPI.svg?style=for-the-badge

[forks-url]: https://github.com/lethaiviet/GmailAPI/graphs/network/members

[stars-shield]: https://img.shields.io/github/stars/lethaiviet/GmailAPI.svg?style=for-the-badge

[stars-url]: https://github.com/lethaiviet/GmailAPI/graphs/stargazers

[issues-shield]: https://img.shields.io/github/issues/lethaiviet/GmailAPI.svg?style=for-the-badge

[issues-url]: https://github.com/lethaiviet/GmailAPI/graphs/issues

[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge

[license-url]:https://github.com/lethaiviet/GmailAPI/graphs/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555