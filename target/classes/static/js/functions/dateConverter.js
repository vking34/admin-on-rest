
export const dateFormatter = v => {
    // v is a string format like "Wed Jan 10 08:46:04 ICT 2018"

    const match = /(\D{3}) (\D{3}) (\d{2}) (\d{2}):(\d{2}):(\d{2}) ICT (\d{4})/.exec(v);

    if(match === null) return;

    let month = 1;
    switch (match[2]) {
        case 'Jan':{
            month = 1;
            break;
        }
        case 'Feb':{
            month = 2;
            break;
        }
        case 'Mar':{
            month = 3;
            break;
        }
        case 'Apr':{
            month = 4;
            break;
        }
        case 'May':{
            month = 5;
            break;
        }
        case 'Jun':{
            month = 6;
            break;
        }
        case 'Jul':{
            month = 7;
            break;
        }
        case 'Aug':{
            month = 8;
            break;
        }
        case 'Sep':{
            month = 9;
            break;
        }
        case 'Oct':{
            month = 10;
            break;
        }
        case 'Nov':{
            month = 11;
            break;
        }
        case 'Dec':{
            month = 12;
            break;
        }
    }
    --month;
    const day = new Date(match[7], month, match[3], match[4], match[5], match[6]);

    if (isNaN(day)) return;
    return day;
};

const daysOfWeek = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
const pad = '00';

export const dateParser = v => {
    // v is a string form of "2019-03-15T17:00:00.000Z"

    if(typeof v === "undefined")
        return;

    const match = /(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}).(\d{3})Z/.exec(v);

    let year = match[1];
    let mon = parseInt(match[2]);
    let day = (parseInt(match[3]) + 1).toString();
    let d = new Date(v);

    let month = '';
    switch (mon) {
        case 1: {
            month = 'Jan';
            break;
        }
        case 2: {
            month = 'Feb';
            break;
        }
        case 3: {
            month = 'Mar';
            break;
        }
        case 4: {
            month = 'Apr';
            break;
        }
        case 5: {
            month = 'May';
            break;
        }
        case 6: {
            month = 'Jun';
            break;
        }
        case 7: {
            month = 'Jul';
            break;
        }
        case 8: {
            month = 'Aug';
            break;
        }
        case 9: {
            month = 'Sep';
            break;
        }
        case 10: {
            month = 'Oct';
            break;
        }
        case 11: {
            month = 'Nov';
            break;
        }
        case 12: {
            month = 'Dec';
            break;
        }
    }

    return `${daysOfWeek[d.getDay()]} ${month} ${(pad + day).slice(-2)} 00:00:00 ICT ${year}`;
};