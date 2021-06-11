
const listElem = document.querySelector('#list');



function getListAjax(){
    fetch('fav')
        .then(res => res.json())
        //화살표 뒤에 바로 적어주면 return과 같은 효과
        .then(myJson => { //중괄호 있음 그냥 머..,,
            console.log(myJson);
            makeView(myJson);
        });
}

function makeView(data){
    listElem.innerHTML = '';

    const table = document.createElement('table');
    listElem.append(table);

    table.innerHTML=` 
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
    </tr>`;
    //``를 쓰게 되면 편하다

    data.forEach(item => {
        const tr = document.createElement('tr');
        table.append(tr);

        tr.classList.add('record');
        tr.addEventListener('click',()=> {
            moveToDetail(item.iboard);
        })

        let imgSrc = '/res/img/noprofile.jpeg';
        if(item.profileImg != null){
            imgSrc = `/img/${item.iuser}/${item.profileImg}`;
        }

        tr.innerHTML = `
        <td>${item.iboard}</td>
        <td>${item.title}</td>
        <td>${item.writerNm}<img src="${imgSrc}"></td>
        <td>${item.regdt}</td>
        </td>
        `;

    });

    function moveToDetail(iboard){
        location.href = '/board/detail?iboard='+iboard;
    }
}


getListAjax();

