// dt_get.js
let dt = new Date(2022, 11, 4, 20, 7, 15, 365);
console.log(dt);
console.log(dt.getFullYear());       // 2022
console.log(dt.getMonth());          // 11
console.log(dt.getDate());           // 4
console.log(dt.getDay());            // 0 Sunday
console.log(dt.getHours());          // 20
console.log(dt.getMinutes());        // 7
console.log(dt.getSeconds());        // 15
console.log(dt.getMilliseconds());   // 365
console.log(dt.getTime());           // 1670152035365
console.log(dt.getTimezoneOffset()); // -540 = - (9 hours * 60 minutes)
