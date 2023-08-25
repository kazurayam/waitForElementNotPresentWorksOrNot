// dt_add.js

let dt = new Date(2022, 11, 15, 20, 40, 0);
console.log(dt.toLocaleString());

dt.setMonth(dt.getMonth() + 3);
console.log(dt.toLocaleString());

dt.setDate(dt.getDate() - 20);
console.log(dt.toLocaleString());
