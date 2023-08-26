- Table of contents
{:toc}

# WebUI.waitForElementNotPresent keyword --- was it fixed at v8.6.0 or not?

## Problem to solve

In the Katalon User forum, there was a post
<https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476>. The original post reported that, at v8.6.0, [WebUI.waitForElementPresent](https://docs.katalon.com/docs/create-tests/keywords/keyword-description-in-katalon-studio/web-ui-keywords/webui-wait-for-element-not-present) keyword failed to work.

Later, @vu.tran reported that, at v8.6.5, [the bug was fixed](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476/30).

However, [@qa113 wrote](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476/31) that the bug still remains in v8.6.5.

The bug of waitForElementNotPresent keyword --- is it fixed or not?

# Solution

I have developed a Katalon Studio project [kazurayam/waitForElementNotPresentWorksOrNot](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot). With it, I checked how `waitForElementNotPresent` keyword worked in Katalon Studio version 8.6.0, and also checked how the keyword works in ks v8.6.5.

Based on that study, I would conclude that the problem was fixed at v8.6.5. I guess, [@qa113's post](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476/31) is another issue.

# Description

## Download the project

You can download the zip of this project from the [Releases]()(<https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/releases>) page, unzip it, open it with your local Katalon Studio.

## Application Under Test

-   [Application Under Test](https://kazurayam.github.io/waitForElementNotPresentWorksOrNot)

<figure>
<img src="https://kazurayam.github.io/waitForElementNotPresentWorksOrNot/images/AUT_countdownclock.png" alt="Countdown Clock" />
</figure>

<figure>
<img src="https://kazurayam.github.io/waitForElementNotPresentWorksOrNot/images/AUT_gone.png" alt="GONE" />
</figure>

The project contains an HTML file \[page.html\](<https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/page.html>) which displays a countdown clock which will expire quickly in 5 seconds. When the clock expires, an HTML element with id="demo" will be removed.

The project contains a script \[Test Case/TC1\](<https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/Scripts/TC1/Script1692936068230.groovy>). This script opens the page.html in a browser. The script calls "waitForElementNotPresent" keyword to check the presense of the HTML element with id="demo" while specifying the timeout 10 seconds. If the keyword could detect that the element has disappeared, the test case will PASS. If the keyword failed to detect it, the test case will FAIL.

I tried to run the TC1 using several versions of Katalon Studio

-   v8.6.6 currently latest version

-   v8.6.0 which the original post reported the defect

-   v8.4.1 with which @kazurayam compared the source code against v8.6.0

I used Firefox browser for this experiment. I refrained from Chrome browser v115 and newers because Chrome recently made a \[changed\](<https://forum.katalon.com/t/as-of-chrome-115-tools-update-webdrivers-chrome-no-longer-works/93200>) that makes it difficult to compare the versions.

# Reproducing the problem with v8.6.0

I installed v8.6.0; updated the WebDriver for Firefox; ran "Test Cases/TC1". See the following screenshot.
