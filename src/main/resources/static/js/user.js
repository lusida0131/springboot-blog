let index = {
    init: function() {
        $("#btn-save").on("click", ()=> {
            this.save();
        })
    },
    save: function() {
        //alert('user의 save함수 호출 됨');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        //console.log(data);
        // ajax 호출 시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        $.ajax({
            //회원가입 수행 요청
            type: "POST",
            url: "/blog/api/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8", // body data가 어떤 타입인지
            dataType: "json" // 응답
        }).done(function(resp) {
            alert("회원가입이 완료 되었습니다.");
            location.href= "/blog";
        }).fail(function(error) {
            alert(JSONt.sringify(error));
        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경해서 insert 요청
    }
}
index.init();