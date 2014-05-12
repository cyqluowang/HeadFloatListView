
/*
 * GET home page.
 */



exports.index = function(req, res){

       function  say(world){
           console.log("woshiyige ---tianai"+world)
       }

    function excute(a,b){
        a(b);
    }

    excute(say,"haha")

    excute(function(a){console.log(a)},"papap")


  res.render('index', { title: "fff"});
};