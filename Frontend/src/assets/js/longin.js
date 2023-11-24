window.onload = function(){
    if(localStorage.getItem("login")){
    var id = localStorage.getItem("login")
        if(id=="admin"){
            document.querySelector("#loout").setAttribute("style", "display:none");
            document.querySelector("#admin").setAttribute("style", "display:flex");
        }else{
            var name = JSON.parse(localStorage.getItem(id)).name;
            document.querySelector("#loout").setAttribute("style", "display:none");
            document.querySelector("#loin").setAttribute("style", "display:flex");
            document.querySelector("#welcome").innerHTML = name+"님 환영합니다";
        }
    }else{
    document.querySelector("#loout").setAttribute("style", "display:flex");
    document.querySelector("#loin").setAttribute("style", "display:none");
    }
    if (window.location.href.split('/')[3] == 'update.html'||window.location.href.split('/')[3] == 'update.html#'){
        var id = localStorage.getItem("login")
        var user = JSON.parse(localStorage.getItem(id));
        document.querySelector("#id").value = user.id;
        document.querySelector("#name").value = user.name;
        document.querySelector("#email").value = user.email;
        document.querySelector("#age").value = user.age;
        document.querySelector("#id").setAttribute("readonly",true);
    }
    if (window.location.href.split('/')[3] == 'manager.html'||window.location.href.split('/')[3] == 'manager.html#') {
        for (let i = 0; i < localStorage.length; i++) {
            const key = window.localStorage.key(i);
            if(key=="login") continue;
            if(key=="cities") continue;
            const value = window.localStorage.getItem(key);
            var person = JSON.parse(value);

            let tr = document.createElement("tr");
            tr.setAttribute("id", `product${i}`);
            let str = ``;
            str += `<td class="p-5">${person.id}</td>`;
            str += `<td class="p-5">${person.name}</td>`;
            str += `<td class="p-5">${person.email}</td>`;
            str += `<td class="p-5">${person.age}</td>`;
            str += `<td class="p-5"><button onclick="del('${i}')">삭제</button></td>`
            tr.innerHTML = str;

            document.querySelector(".table tbody").appendChild(tr);
        }
        
    }

    
  }

  function del(n){
    let el = document.querySelector(`#product${n}`);
    let id = document.querySelector(`#product${n} td:first-child`).innerHTML;
    document.querySelector("tbody").removeChild(el);
    localStorage.removeItem(id);
}

function login(){
    let id = document.getElementById("Lid").value;
    let password = document.getElementById("Lpassword").value;
    if(id=="ssafy"&&password==1234){
        localStorage.setItem("login","admin");
        document.location.href = "index.html";
        return;
    }
    let js = JSON.parse(localStorage.getItem(id));
    if(js&&js.password==password){
        window.alert("로그인 성공!");
        var logg = document.getElementById("logg");
        localStorage.setItem("login",id);
        window.location.href="index.html";
    }else{
        window.alert("로그인 실패");
    }
}
function logout(){
    localStorage.removeItem("login");
    document.location.href = "index.html";
}

function regist() {
    let id = document.getElementById("id").value;
    let password = document.getElementById("password").value;
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let age = document.getElementById("age").value;

    if (!id||!password||!name||!email||!age) {
      alert("빈칸이 없도록 입력해주세요.");
      return;
    } else {
      const user = {
        id: id,
        password: password,
        name: name,
        email: email,
        age: age,
      };
  
      localStorage.setItem(id, JSON.stringify(user));
      alert("사용자 등록 성공!");
      document.location.href = "login.html";
    }
  }

  function edit() {
    let id = document.getElementById("id").value;
    let password = document.getElementById("password").value;
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let age = document.getElementById("age").value;

    if (!id||!password||!name||!email||!age) {
      alert("빈칸이 없도록 입력해주세요.");
      return;
    } else {
      const user = {
        id: id,
        password: password,
        name: name,
        email: email,
        age: age,
      };
  
      localStorage.setItem(id, JSON.stringify(user));
      alert("수정 성공!");
      document.location.href = "index.html";
    }
  }

  function drop(){
    var flag = window.confirm("정말 탈퇴하시겠습니까?");
    if(flag){
        var id = localStorage.getItem("login");
        localStorage.removeItem(id);
        localStorage.removeItem("login");
        document.location.href = "index.html";
    }
  }

