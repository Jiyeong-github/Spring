
const listElem = document.querySelector('#list');
const pagingElem = document.querySelector('#paging');


function getListAjax(page=1){
    fetch('fav?page='+page)
        .then(res => res.json())
        //화살표 뒤에 바로 적어주면 return과 같은 효과
        .then(myJson => { //중괄호 있음 그냥 머..,,
            console.log(myJson.list);
            makeView(myJson.list);
            makePaging(myJson.maxPageVal);
        });
}

//페이징 view 만들기 - 정수 값이 넘어 옴 (좋아요의 수에 따라)
function makePaging(maxPageVal,selectedPage){
 for(let i=0; i<=maxPageVal; i++){
     const span = document.createElement('span');
     if(selectedPage === i){
         span.classList.add('selected');
     }else{
         span.classList.add('pointer');
         span.addEventListener('click',function (){
             getListAjax(i);
         })
     }
     span.innerText = i;
     span.addEventListener('click',function (){
         getListAjax(i);
     })
     pagingElem.append(span);
 }
}

//리스트 view 만들기
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

