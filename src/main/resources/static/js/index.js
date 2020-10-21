var index = {
    init : function() {
        var _this = this;

        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        })
    },
    save : function () {
        var data = {
            username: $('#username').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/members',
            dataType: 'text',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('멤버를 추가했습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update : function () {
        var data = {
            username: $('#username').val()
        };

        var id = $('#memberId').val();

        $.ajax({
            type: 'PUT',
            url: '/api/members/' + id,
            dataType: 'text',
            contentType: 'application/json; charset=utf8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("멤버 이름을 수정했습니다.");
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
};

index.init();