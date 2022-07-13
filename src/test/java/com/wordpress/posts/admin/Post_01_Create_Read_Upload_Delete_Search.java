package com.wordpress.posts.admin;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.wordpress.*;
import pageUIs.wordpress.PageGeneratorManager;

public class Post_01_Create_Read_Upload_Delete_Search extends BaseTest {
    private WebDriver driver;
    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminPostSearchPO adminPostSearchPage;
    private AdminPostAddNewPO adminPostAddNewPage;
    private UserHomePO userHomePage;
    private UserPostDetailsPO userPostDetailsPage;
    private UserPostSearchPO userPostSearchPage;
    private String adminPageUrl, userPageUrl, postSearchUrl, adminUserName, adminPassword, postTitle,
            postBody, authorNickname, postDate, editPostTitle, editPostBody;

    @Parameters({"browser", "adminUrl", "userUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String adminPageUrl, String userPageUrl) {
        this.adminPageUrl = adminPageUrl;
        this.userPageUrl = userPageUrl;
        postDate = getToday();

        adminUserName = "automationfc";
        adminPassword = "automationfc";
        long postNumber = generateRandomNumber();
        postTitle = "Live coding title no." + postNumber;
        postBody = "Live coding body no." + postNumber;
        authorNickname = "automationfc";
        editPostTitle = "Edit title no." + postNumber;
        editPostBody = "Edit body no." + postNumber;

        log.info("Pre-condition - Step 01: Open browser and navigate to admin site");
        driver = getBrowserDriver(browserName, adminPageUrl);
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

        log.info("Pre-condition - Step 02: Input to Username textbox with value: " + adminUserName);
        adminLoginPage.inputToUserNameTextbox(adminUserName);

        log.info("Pre-condition - Step 03: Input to Password textbox with value: " + adminPassword);
        adminLoginPage.inputToPasswordTextbox(adminPassword);

        log.info("Pre-condition - Step 04: Click to Login button");
        adminDashboardPage = adminLoginPage.clickLoginButton();
    }

    @Test
    public void Post_01_Create_New_Post() {
        log.info("Create_post - Step 01: Click to 'Posts' menu link");
        adminPostSearchPage = adminDashboardPage.clickPostsMenuLink();

        log.info("Create_post - Step 02: Get Post search page url");
        postSearchUrl = adminPostSearchPage.getPageUrl(driver);

        log.info("Create_post - Step 03: Click to 'Add new' button");
        adminPostAddNewPage = adminPostSearchPage.clickAddNewButton();

        log.info("Create_post - Step 04: Close welcome popup if exists");
        adminPostAddNewPage.closeWelcomePopup();

        log.info("Create_post - Step 05: Input to post title block with value: " + postTitle);
        adminPostAddNewPage.inputToPostTitleTextbox(postTitle);

        log.info("Create_post - Step 06: Input to post body block with value: " + postBody);
        adminPostAddNewPage.inputToPostBodyTextbox(postBody);

        log.info("Create_post - Step 07: Click to 'Publish' button");
        adminPostAddNewPage.clickPublishOrUpdateButton();

        log.info("Create_post - Step 08: Click to 'Publish' button at pre-publish info");
        adminPostAddNewPage.clickPrePublishButton();

        log.info("Create_post - Step 09: Verify Post published message displayed");
        verifyTrue(adminPostAddNewPage.isPostPublishedOrUpdatedMessageDisplayed("Post published."));
    }

    @Test
    public void Post_02_Search_And_View_Post() {
        log.info("Search_View_Post - Step 01: Open Admin Search post page");
        adminPostAddNewPage.openPageUrl(driver, postSearchUrl);
        adminPostSearchPage = PageGeneratorManager.getAdminPostSearchPage(driver);

        log.info("Search_View_Post - Step 02: Input to Search textbox with value: " + postTitle);
        adminPostSearchPage.inputToSearchTextbox(postTitle);

        log.info("Search_View_Post - Step 03: Click to 'Search Posts' button");
        adminPostSearchPage.clickSearchPostsButton();

        log.info("Search_View_Post - Step 04: Verify displayed message 'Search results for: " + postTitle + "'");
        verifyEquals(adminPostSearchPage.getSearchResultsMessage(), "Search results for: " + postTitle);

        log.info("Search_View_Post - Step 05: Verify title displayed with value: " + postTitle);
        verifyEquals(adminPostSearchPage.getValueByHeaderId("title"), postTitle);

        log.info("Search_View_Post - Step 06: Verify author displayed with value: " + authorNickname);
        verifyEquals(adminPostSearchPage.getValueByHeaderId("author"), authorNickname);

        log.info("Search_View_Post - Step 07: Open end user site");
        adminPostSearchPage.openPageUrl(driver, userPageUrl);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        log.info("Search_View_Post - Step 08: Verify post title displayed with value: " + postTitle);
        verifyTrue(userHomePage.isPostTitleDisplayed(postTitle));

        log.info("Search_View_Post - Step 09: Verify post title '" + postTitle + "' is posted on " + postDate);
        verifyTrue(userHomePage.isPostDateByTitleDisplayed(postTitle, postDate));

        log.info("Search_View_Post - Step 10: Verify post title '" + postTitle + "' is written by " + authorNickname);
        verifyTrue(userHomePage.isPostAuthorByTitleDisplayed(postTitle, authorNickname));

        log.info("Search_View_Post - Step 11: Verify post title '" + postTitle + "' has content: " + postBody);
        verifyTrue(userHomePage.isPostContentByTitleDisplayed(postTitle, postBody));

        log.info("Search_View_Post - Step 12: Click to post title: " + postTitle);
        userPostDetailsPage = userHomePage.clickPostTitleLink(postTitle);

        log.info("Search_View_Post - Step 13: Verify post title with value: " + postTitle);
        verifyTrue(userPostDetailsPage.isPostTitleDisplayed(postTitle));

        log.info("Search_View_Post - Step 14: Verify post date with value: " + postDate);
        verifyTrue(userPostDetailsPage.isPostDateByTitleDisplayed(postTitle, postDate));

        log.info("Search_View_Post - Step 15: Verify post author with value: " + authorNickname);
        verifyTrue(userPostDetailsPage.isPostAuthorByTitleDisplayed(postTitle, authorNickname));

        log.info("Search_View_Post - Step 16: Verify post content with value: " + postBody);
        verifyTrue(userPostDetailsPage.isPostContentByTitleDisplayed(postTitle, postBody));
    }

    @Test
    public void Post_03_Edit_Post() {
        log.info("Edit_Post - Step 01: Open Admin Search post page");
        userPostDetailsPage.openPageUrl(driver, postSearchUrl);
        adminPostSearchPage = PageGeneratorManager.getAdminPostSearchPage(driver);

        log.info("Edit_Post - Step 02: Input to Search textbox with value: " + postTitle);
        adminPostSearchPage.inputToSearchTextbox(postTitle);

        log.info("Edit_Post - Step 03: Click to 'Search Posts' button");
        adminPostSearchPage.clickSearchPostsButton();

        log.info("Edit_Post - Step 04: Click to post title: " + postTitle);
        adminPostAddNewPage = adminPostSearchPage.clickPostTitle(postTitle);

        log.info("Edit_Post - Step 05: Edit post title with value: " + editPostTitle);
        adminPostAddNewPage.inputToPostTitleTextbox(editPostTitle);

        log.info("Edit_Post - Step 06: Edit post body with value: " + editPostBody);
        adminPostAddNewPage.editPostBodyTextbox(editPostBody);

        log.info("Edit_Post - Step 07: Click to 'Update' button");
        adminPostAddNewPage.clickPublishOrUpdateButton();

        log.info("Edit_Post - Step 08: Verify Post updated message displayed");
        verifyTrue(adminPostAddNewPage.isPostPublishedOrUpdatedMessageDisplayed("Post updated."));

        log.info("Edit_Post - Step 09: Open Admin Search post page");
        adminPostAddNewPage.openPageUrl(driver, postSearchUrl);
        adminPostSearchPage = PageGeneratorManager.getAdminPostSearchPage(driver);

        log.info("Edit_Post - Step 10: Input to Search textbox with value: " + editPostTitle);
        adminPostSearchPage.inputToSearchTextbox(editPostTitle);

        log.info("Edit_Post - Step 03: Click to 'Search Posts' button");
        adminPostSearchPage.clickSearchPostsButton();

        log.info("Edit_Post - Step 11: Verify post title displayed with value: " + editPostTitle);
        verifyEquals(adminPostSearchPage.getValueByHeaderId("title"), editPostTitle);

        log.info("Edit_Post - Step 12: Verify author displayed with value: " + authorNickname);
        verifyEquals(adminPostSearchPage.getValueByHeaderId("author"), authorNickname);

        log.info("Edit_Post - Step 07: Open end user site");
        adminPostSearchPage.openPageUrl(driver, userPageUrl);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        log.info("Edit_Post - Step 13: Verify post title displayed with value: " + editPostTitle);
        verifyTrue(userHomePage.isPostTitleDisplayed(editPostTitle));

        log.info("Edit_Post - Step 14: Verify post title '" + editPostTitle + "' is posted on " + postDate);
        verifyTrue(userHomePage.isPostDateByTitleDisplayed(editPostTitle, postDate));

        log.info("Edit_Post - Step 15: Verify post title '" + editPostTitle + "' is written by " + authorNickname);
        verifyTrue(userHomePage.isPostAuthorByTitleDisplayed(editPostTitle, authorNickname));

        log.info("Edit_Post - Step 16: Verify post title '" + editPostTitle + "' has content: " + editPostBody);
        verifyTrue(userHomePage.isPostContentByTitleDisplayed(editPostTitle, editPostBody));
    }

    @Test
    public void Post_04_Delete_Post() {
        log.info("Delete_Post - Step 01: Open Admin Search post page");
        userHomePage.openPageUrl(driver, postSearchUrl);
        adminPostSearchPage = PageGeneratorManager.getAdminPostSearchPage(driver);

        log.info("Delete_Post - Step 02: Input to Search textbox with value: " + editPostTitle);
        adminPostSearchPage.inputToSearchTextbox(editPostTitle);

        log.info("Delete_Post - Step 03: Click to 'Search Posts' button");
        adminPostSearchPage.clickSearchPostsButton();

        log.info("Delete_Post - Step 04: Check to checkbox at post title: " + editPostTitle);
        adminPostSearchPage.checkToCheckboxByPostTitle(editPostTitle);

        log.info("Delete_Post - Step 05: Select 'Move to Trash' option at action dropdown");
        adminPostSearchPage.selectOptionInActionDropdown("Move to Trash");

        log.info("Delete_Post - Step 06: Click to 'Apply' button");
        adminPostSearchPage.clickApplyButton();

        log.info("Delete_Post - Step 07: Verify displayed message '1 post moved to the Trash.'");
        verifyTrue(adminPostSearchPage.isActionMessageDisplayed("1 post moved to the Trash."));

        log.info("Delete_Post - Step 08: Verify 'No posts found.' item at table");
        verifyTrue(adminPostSearchPage.isTableItemDisplayed("No posts found."));

        log.info("Delete_Post - Step 09: Open end user site");
        adminPostSearchPage.openPageUrl(driver, userPageUrl);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        log.info("Delete_Post - Step 10: Input to block search textbox with value: " + editPostTitle);
        userHomePage.inputToSearchTextbox(editPostTitle);

        log.info("Delete_Post - Step 11: Click to Search textbox");
        userPostSearchPage = userHomePage.clickSearchButton();

        log.info("Delete_Post - Step 12: Verify 'Nothing Found' title displayed");
        verifyTrue(userPostDetailsPage.isTitleDisplayed("Nothing Found"));

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
