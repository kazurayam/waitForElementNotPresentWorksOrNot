[Katalon Studio] WebUI.waitForElementNotPresent --- does it work or not?

## Problem to solve

In the Katalon User forum, there was a post
https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476 .

Quoting from the original post by @petr.brezina :

>since I have updated to the new version of Katalon Studio 8.6.0, all test cases fall, because command waitForElementNotPresent is not working properly. If I run the same test case on Katalon Studio 8.5.0, test case passes successfully.

The original post reported that, at v8.6.0, [WebUI.waitForElementPresent](https://docs.katalon.com/docs/create-tests/keywords/keyword-description-in-katalon-studio/web-ui-keywords/webui-wait-for-element-not-present) keyword failed to work.

Later, @vu.tran reported that, at v8.6.5, [the bug was fixed](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476/30).

However, [@qa113 followed](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476/31) saying that, at 8.6.5, the bug still remains.

The bug of waitForElementNotPresent keyword --- is it fixed or not?

## Test fixture

I have developed a [Katalon Studio project](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot).

The project contains an HTML file [page.html](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/page.html) which displays a countdown clock which will expire quickly in 5 seconds. When the clock expires, an HTML element with id="demo" will be removed.

The project contains a script [Test Case/TC1](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/Scripts/TC1/Script1692936068230.groovy). This script opens the page.html in a browser. The script calls "waitForElementNotPresent" keyword to check the presense of the HTML element with id="demo" while specifying the timeout 10 seconds. If the keyword could detect that the element has disappeared, the test case will PASS. If the keyword failed to detect it, the test case will FAIL.

I tried to run the TC1 using several versions of Katalon Studio

- v8.6.6 currently latest version
- v8.6.0 which the original post reported the defect
- v8.4.1 with which @kazurayam compared the source code against v8.6.0

I used Firefox browser for this experiment. I refrained from Chrome browser v115 and newers because Chrome recently made a [changed](https://forum.katalon.com/t/as-of-chrome-115-tools-update-webdrivers-chrome-no-longer-works/93200) that makes it difficult to compare the versions.

I expected that the result will be as follows:

||Katalon Studio version||Test Cases/TC1||
|v8.6.6|PASSED|
|v8.6.5|PASSED|
|v8.6.0|FAILED|
|v8.4.1|PASSED|

## Study Result

I got the following result.

||Katalon Studio version||Test Cases/TC1||Screenshot||
|v8.6.6|PASSED|[img](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/screenshot-8.6.6.208.png)|
|v8.6.5|PASSED|[img](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/screenshot-8.6.5.208.png)|
|v8.6.0||
|v8.4.1||

This results matches exactly what I expected.

Therefore I would express my doubt about the [post](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476/30) by @qa113.
