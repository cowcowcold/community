function postComment() {
    let questionId = $("#question_id").val();
    let content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1,
        }),
        success: function (response) {
            if (response.code == 200) {
                $("#comment_section").hide();
            } else {
                if (response.code == 2003) {
                    //登录异常
                    var isAccepted = confirm(response.message());
                    if(isAccepted==true){
                        window.open("https://github.com/login/oauth/authorize?client_id=1146ae3847d5848633fa&redirect_uri=http://localhost:7787/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }else {

                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}