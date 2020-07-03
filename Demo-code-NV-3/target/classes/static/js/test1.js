var load = new Vue({
    el: "#data1",
    data: {

        datainsert: { 
            "name": "",
            "ngaysinh": null,
            "email": "",
            "username": "",
            "passwork": "",
            "diachi": "",
            "id": 0,
            "manvql": window.sessionStorage.getItem('manvql')
        
        }
    },
    methods: {

        save: function() {
            
          
          
                axios.post("api/contact/", this.datainsert).then(function(response) {
                    console.log(this.datainsert);

                     window.location.href = "test1.html";
                    })
                    .catch(function(error) {
                        console.log(error);
                    });
           

        },

    }

});
