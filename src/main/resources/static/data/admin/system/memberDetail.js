function fn_changeApproval(approval) {

    let url = "/admin/operation/member/api/approval"
    let pid = $("#pid");

    if(approval === 'Y'){
        if(confirm("승인 하시겠습니까?")) {
            $.ajax({
                type : 'get' ,
                url : url ,
                contentType : "application/json" ,
                data : {"approval" : approval ,
                        "pid" : pid.val()} ,
                dataType : 'text' ,
                success : function (data) {
                    alert("승인 되었습니다.");
                    location.reload();
                }

            });
        } else {
            alert("승인처리가 취소 되었습니다.")
        }
    }

    if(approval === 'N'){
        if(confirm("거부 하시겠습니까?")) {
            $.ajax({
                type : 'get' ,
                url : url ,
                contentType : "application/json" ,
                data : {"approval" : approval ,
                        "pid" : pid.val()} ,
                dataType : 'text' ,
                success : function (data) {
                    alert("거부 되었습니다.");
                    location.reload();
                }

            });

        } else {
            alert("거부처리가 취소 되었습니다.")
        }
    }
}