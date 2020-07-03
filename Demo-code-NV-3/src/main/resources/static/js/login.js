var login = new Vue({
    el: '#login',
    data: {
        data: {},
        userlogin: {  "username": "",
            "passwork": ""},
            load:{}
    },
    methods: {
        dologin: function() {
        	console.log(this.userlogin);
            axios.post("/api/user/loginAPI", this.userlogin).then(function(response) {
               // console.log(response.data);
             this.load = response.data;
             console.log(response.data);
             
           
             if(load.code==1){
                window.sessionStorage.setItem('Authorization', load.message);
                window.location.href = "test1.html"
             }
             else{
                alert("login khong thanh cong");
             }
            });
        }
    }
})