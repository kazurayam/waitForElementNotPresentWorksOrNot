- Table of contents
{:toc}

# WebUI.waitForElementNotPresent keyword --- was it fixed at v8.6.0 or not?

## Problem to solve

In the Katalon User forum, there was a post
["Katalon Studio 8.6.0 - waitForElementNotPresent - bug - timeout"](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476). The original post reported that, at v8.6.0, [WebUI.waitForElementPresent](https://docs.katalon.com/docs/create-tests/keywords/keyword-description-in-katalon-studio/web-ui-keywords/webui-wait-for-element-not-present) keyword failed to work. Later, @vu.tran reported that, at v8.6.5, [the bug was fixed](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476/30). However, [@qa113 wrote](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476/31) that the bug still remains in v8.6.5.

The bug of waitForElementNotPresent keyword --- is it fixed at v8.6.5 or not?

## What I have done

I have developed a Katalon Studio project [kazurayam/waitForElementNotPresentWorksOrNot](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot). With it, I examined how `WebUI.waitForElementNotPresent` keyword works in v8.4.1, v8.6.0 and v8.6.5.

### How to download the project

You can download the zip of this project from the [Releases](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/releases) page, unzip it, open it with your local Katalon Studio.

### Application Under Test

You can see our Application Under Test at

-   <https://kazurayam.github.io/waitForElementNotPresentWorksOrNot/page.html>

The page shows a Countdown clock. The clock has a countdown distance of 10 seconds as default.

<figure>
<img src="https://kazurayam.github.io/waitForElementNotPresentWorksOrNot/images/AUT_countDownClock.png" alt="Countdown Clock" />
</figure>

Internally the HTML contains an P element with id="clock" as this:

    <p id="clock">0d 0h 0m 8s</p>

When the countdown clock reaches zero, then the clock will disappear. A message "Gone!" comes up.

<figure>
<img src="https://kazurayam.github.io/waitForElementNotPresentWorksOrNot/images/AUT_gone.png" alt="GONE" />
</figure>

Internally, the `<p id="clock">0d 0h 0m Xs</p>` element will be removed (becomes not present) when the clock reached zero.

### Test scripts

Please read the source codes for detail.

-   [Test Cases/TC1\_d10\_t7](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/Scripts/TC1_d10_t7/Script1693013953158.groovy)

-   [Test Cases/TC2\_d10\_t14](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/Scripts/TC2_d10_t13/Script1693013995141.groovy)

-   [com.kazurayam.ks.waitforelementnotpresent.KeywordDriver](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/Keywords/com/kazurayam/ks/waitforelementnotpresent/KeywordDriver.groovy)

-   [com.kazurayam.ks.waitforelementnotpresent.ResultEvaluator](https://github.com/kazurayam/waitForElementNotPresentWorksOrNot/blob/master/Keywords/com/kazurayam/ks/waitforelementnotpresent/ResultEvaluator.groovy)

You can execute “Test Suites/TS1” which will execute “Test Cases/TC1\_d10\_t7” and “Test Cases/TC2\_d10\_t13” in this sequence. The `TS1` is enabled to take movies in MOV format. I converted the MOV movies to MP4, and posted them to YouTube.

### Study

I developed a set of test scripts in Katalon Studio which employs the `WebUI.waitForElementNotPresent(TestObject, int timeout)` keyword.

The scripts uses 2 terms with special meaning. So let me define them.

-   **distance** --- The countdown clock starts with the given distance (default=10), goes down by 1 second until it reaches 0.

-   **timeout** --- The `WebUI.waitForElementNotPresent` keyword requires an integer as 2nd argument "timeout".

I have developed 2 cases of verification.

**Test Case 1** : The distance is 10 seconds, timeout is **7** seconds. The target HTML element "clock" will still be there in the page when the timeout expires. The clock will remain in the page view. In this case, the keyword should stop immediately after the timeout. The keyword should return `false` to the caller script.

**Test Case 2** : The distance is 10 seconds, timeout is **13** seconds. The target HTML element "clock" will disappear before the timeout expires. The clock will fade and a message "GONE" will comes up in the page view. In this case, *the keyword should stop immediately after the timeout*. The keyword should return `true` to the caller script.

#### Case where the HTML element is still present when the keyword gets timed out

In this case, the `WebUI.waitForElementNotPresent` keyword worked just as expected in v8.4.1, v8.6.0 and v8.6.5.

[see the Movie (v8.4.1 TC2\_d10\_t7)](https://youtu.be/Uv3eeN5KHpM)

#### Case where the HTML element disappears before the keyword gets timed out

This case is interesting. The `WebUI.waitForElementNotPresent` worked as expected in v8.4.1. But it started to behave strange in v8.6.0. And in v8.6.5, the problem was fixed.

##### v8.4.1

It worked OK in v8.4.1.

[see the Movie (v8.4.1 TC2\_d10\_t13)](https://youtu.be/AhmfkfkQMoA)

##### v8.6.0

The `WebUI.waitForElementNotPresent` behaves strange in v8.6.0. When the AUT page is opened, it starts displaying a countdown clock. After 10 seconds, the countdown expires; the clock disappears; a message "Gone" comes up in turn. The keyword would detect the clock is no longer there. We expect the keyword to return immediately when the clock disappears. However, **the keyword in v8.6.0 doesn’t. The keyword blocked for more seconds**. This is the problem which [@petr.brezina](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476) pointed out.

[See the Movie (v8.6.0 TC2\_d10\_t13)](https://youtu.be/MGYcN6ea_Jo)

##### v8.6.5

The `WebUI.waitForElementNotPresent` keyword in v8.6.5 behaves as expected.

[See v8.6.5 TC2\_d10\_t13](https://youtu.be/EdzfIg0AqaU)

## Conclusion

Based on the findings in the examination, I would conclude that the [original problem](https://forum.katalon.com/t/katalon-studio-8-6-0-waitforelementnotpresent-bug-timeout/85476) raised by @petr.brezina was really fixed at v8.6.5.
