var frmElem = document.querySelector('#frm');
//''는 적으면 태그 선택자 '#' 아이디`` '.' 클래스
var uidElem = frmElem.uid;
var upwElem = frmElem.upw;
var chkUpwElem = frmElem.chkupw;
var unmElem = frmElem.unm;
	
//frmElem.querySelector <<<frmElem.btnChkId;
//frmElem이 아닌 frm으로 해도 됨. 하지만 바로 서버에 올라감

var chkUidElem = frmElem.querySelector('#chkuid');
var btnChkIdElem = frmElem.btnChkId; //중복ID체크 버튼
btnChkIdElem.addEventListener('click',function(){
	idChkAjax(uidElem.value);
});

function idChkAjax(uid){
	console.log(uid);
	chkUidElem.innerText = '이 아이디는 사용할 수 있습니다';
	
	fetch('/user/idChk?uid='+uid)
	.then(function(res){
		return res.json();
	})
	.then(function(myJson){
		console.log(myJson);
		switch(myJson.result){
			case '0':
			chkUidResultElem.innerText = '사용 가능';
			break;
			case  '1':
			chkUidResultElem.innerText = '사용 뷸가능';
			break;
		}
	});
}

function frmChk() {
	//이상이 생기면 return false;
	var uidVal = uidElem.value; //적힌 값을 복사해서 줌
	//아이디 미작성 시 alert "아이디를 작성해 주세요" return false
	//2자 이하 alert "아이디는 3자 이상 작성해 주세요" return false 
	if (uidVal.length < 3) {
		if (uidVal.length == 0) {
			alert('아이디를 작성해 주세요');
		} else {
			alert('아이디를 3자 이상 작성해 주세요');
		}
		return false;
	}

	var upwVal = upwElem.value;
	var chkUpwVal = chkUpwElem.value;
	// var unmVal = unmElem.value;
	//비밀번호 미작성시 alert "비밀번호를 작성해 주세요" return false
	//3자 이하면 alert "비밀번호는 4자 이상 작성해 주세요" return false
	//비밀번호와 확인 비번이 다를 때 "비밀번호를 확인해 주세요" return false


	if (upwVal.length <3) {
		if (upwVal.length == 0) {
			alert('비밀번호를 작성해 주세요');
		} else {
			alert('비밀번호를 4자 이상 작성해주세요');
		}
		return false;
	} else if (upwVal !== chkUpwVal) {
		alert('비밀번호를 확인해주세요');
		return false;
	} if (unmElem.value.length < 2) { //.value해줘야 스트링값을 가져옴
		alert('이름은 2자 이상 작성해 주세요');
		return false;
	}

}

