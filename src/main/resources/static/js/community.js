function postComment() {
    let questionId = $("#question_id").val();
    let content = $("#comment_content").val();
    let flag = true;
    if (!content) {
        alert("评论不能为空哦！");
        return;
    }
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
                if (flag) {
                    window.location.reload();
                }
                $("#comment_section").hide();
            } else {
                if (response.code == 2003) {
                    //登录异常
                    flag = false;
                    var isAccepted = confirm(response.message());
                    if (isAccepted == true) {
                        window.open("https://github.com/login/oauth/authorize?client_id=1146ae3847d5848633fa&redirect_uri=http://localhost:7787/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    } else {

                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}