- Table of contents
{:toc}

# WebUI.waitForElementNotPresent keyword --- was it fixed at v8.6.0 or not?

## Problem to solve

In the Katalon User forum, there was a post
["Katalon Studio 8.6.0 - waitForElementNotPresent - bug - timeout"](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476). The original post reported that, at v8.6.0, [WebUI.waitForElementPresent](https://docs.katalon.com/docs/create-tests/keywords/keyword-description-in-katalon-studio/web-ui-keywords/webui-wait-for-element-not-present) keyword failed to work. Later, @vu.tran reported that, at v8.6.5, [the bug was fixed](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476/30). However, [@qa113 wrote](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476/31) that the bug still remains in v8.6.5.

The bug of waitForElementNotPresent keyword --- is it fixed at v8.6.5 or not?

## Solution

I have developed a Katalon Studio project [kazurayam/waitForElementNotPresentWorksOrNot](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot). With it, I examined how `WebUI.waitForElementNotPresent` keyword. How does the keyword works in v8.6.0 and in v8.6.5.

Based on the findings in the examination, I would conclude that the problem was already fixed at v8.6.5.

## Description

What was the original issue? How can I conclude the problem was fixed already? --- I will describe it as follows.

### Download the project

You can download the zip of this project from the [Releases](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/releases) page, unzip it, open it with your local Katalon Studio.

### Application Under Test

You can see our Application Under Test at <https://kazurayam.github.io/waitForElementNotPresentWorksOrNot/page.html>

The page shows a Countdown clock. The clock has a countdown distance of 10 seconds as default.

<figure>
<img src="https://kazurayam.github.io/waitForElementNotPresentWorksOrNot/images/AUT_countDownClock.png" alt="Countdown Clock" />
</figure>

Internally the HTML contains an P element with id="demo" as this:

    <p id="demo">0d 0h 0m 8s</p>

When the countdown clock reaches zero, then the clock will disappear. A message "Gone!" comes up.

<figure>
<img src="https://kazurayam.github.io/waitForElementNotPresentWorksOrNot/images/AUT_gone.png" alt="GONE" />
</figure>

Internally, the `<p id="demo">0d 0h 0m Xs</p>` element will be removed (becomes not present) when the clock reached zero.

### Test scripts

Please read the source codes for detail.

-   [Test Cases/TC1\_d10\_t7](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/Scripts/TC1_d10_t7/Script1693013953158.groovy)

-   [Test Cases/TC2\_d10\_t14](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/Scripts/TC2_d10_t13/Script1693013995141.groovy)

-   [com.kazurayam.ks.waitforelementnotpresent.KeywordDriver](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/Keywords/com/kazurayam/ks/waitforelementnotpresent/KeywordDriver.groovy)

-   [com.kazurayam.ks.waitforelementnotpresent.ResultEvaluator](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/Keywords/com/kazurayam/ks/waitforelementnotpresent/ResultEvaluator.groovy)

## Examining

### using Katalon Studio v8.6.0

#### Test Cases/TC1\_d10\_t7

10 seconds of clock distance, 7 seconds of kewyord timeout

[Movie of v8.6.0 TC1\_d10\_t7](https://youtu.be/SMwrdctzoV4)

#### Test Cases/TC2\_d10\_t13

10 seconds of distance, timeout: 13secs
[Movie of v8.6.0 TC2\_d10\_t13](https://youtu.be/Wx_MCK0QnMk)

### using Katalon Studio v8.6.5

#### Test Cases/TC2\_d10\_t13

[Movie of v8.6.5 TC2\_d10\_t13](https://youtu.be/jY1ESJ1H21w)
