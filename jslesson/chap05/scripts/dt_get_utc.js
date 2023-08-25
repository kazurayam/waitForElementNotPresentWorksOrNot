// dt_get_utc.js
let dt = new Date(2022, 11, 4, 20, 7, 15, 365);
console.log(dt);
console.log(dt.getUTCFullYear());       // 2022
console.log(dt.getUTCMonth());          // 11
console.log(dt.getUTCDate());           // 4
console.log(dt.getUTCDay());            // 0 Sunday
console.log(dt.getUTCHours());          // 11 = 20 - TimezoneOffset(9)
console.log(dt.getUTCMinutes());        // 7
console.log(dt.getUTCSeconds());        // 15
console.log(dt.getUTCMilliseconds());   // 365
