/**
 * Created with JetBrains WebStorm.
 * User: cyq
 * Date: 13-9-27
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */


post = function(url,obj){
     var result="";
    var http = require('http');
    var querystring = require('querystring');

    if (obj === null) {
        return result
    }

    if (typeof obj === 'object') {

    var post_data = querystring.stringify(obj);
    var options = {
        host: 'mapi.19lou.com/',
        port: 80,
        path: url,
        method: 'POST' ,
        headers: {
            "Content-Type": 'application/x-www-form-urlencoded',
            "Content-Length": post_data.length
        }
    };


    var req = http.request(options, function(res) {
        console.log('STATUS: ' + res.statusCode);
        console.log('HEADERS: ' + JSON.stringify(res.headers));
        res.setEncoding('utf8');
        res.on('data', function (chunk) {
            console.log('BODY: ' + chunk);
        });
    });



    req.write(post_data + "\n");
    req.end();
    }

}