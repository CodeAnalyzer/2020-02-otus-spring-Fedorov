window.onload = pageLoader;

function pageLoader() {

  $("title").remove();
  $("head").append(`<title>Моя библиотека</title>`);

  $("body").empty();
  $("body").append(`
    <div id="controlPanel">
      <button id="insertBook" onclick="editBookForm(0)" style="width: 20%" >Добавить книгу</button>
    </div>
    <h1>Список книг</h1>
      <div class = "listBook"></div>
   `);

   $.get("/books").done(function (books) {

      $("div.listBook").empty();
      $('div.listBook').append(`
          <table id="bookTable" border = "2">
            <thead>
                <tr>
                    <td width = "100px">ID</td>
                    <td width = "400px">Название</td>
                    <td width = "200px">Автор</td>
                    <td width = "150px">Жанр</td>
                </tr>
            </thead>
          </table>
        `);

      books.forEach(function (book) {
        $('div.listBook').append(`
            <table id="bookTable" border = "2">
                <tbody>
                    <tr>
                        <td width = "100px">${book.bookID}</td>
                        <td width = "400px"><a class="link" onclick="editBookForm(${book.bookID})">${book.title}</a></td>
                        <td width = "200px">${book.author.name}</td>
                        <td width = "150px">${book.genre.name}</td>
                    </tr>
                </tbody>
           </table>
        `);
      });
    });
}

function editBookForm(bookID){

  if (bookID != 0) {
    $("head").append(`<title>Редактирование книги</title>`);
    $.get("/books/"+bookID).done(function (book) {
        editBook(book)
      });
  }
  else {
    $("head").append(`<title>Добавление книги</title>`);
    createBook();
  };
}

function editBook(book){

  $("body").empty();
  $("body").append(`

      <h3>
         <a class="link" onclick="pageLoader()">Вернуться к таблице</a>
      </h3>

      <h1>Редактирование книги</h1>

      <div>
        <p>Название</p>
        <input id="BookTitle" type="text" class="name" required placeholder="введите название книги" value="${book.title}">
        <p>Автор</p>
        <input id="Author"    type="text" class="name" required placeholder="ФИО автора книги"       value="${book.author.name}">
        <p>Жанр</p>
        <input id="Genre"     type="text" class="name" required placeholder="наименование жанра"     value="${book.genre.name}">
      </div>

      <div id="modif" align="right" >
        <button onclick="updateBook(${book.bookID})">Изменить</button>
        <button onclick="deleteBook(${book.bookID})">Удалить</button>
      </div>
  `);

}

function createBook(){
  $("body").empty();
  $("body").append(`

      <h3>
         <a class="link" onclick="pageLoader()">Вернуться к таблице</a>
      </h3>

      <h1>Добавление книги</h1>

      <div>
        <p>Название</p>
        <input id="BookTitle" type="text" class="name" required placeholder="введите название книги" value="">
        <p>Автор</p>
        <input id="Author"    type="text" class="name" required placeholder="ФИО автора книги"       value="">
        <p>Жанр</p>
        <input id="Genre"     type="text" class="name" required placeholder="наименование жанра"     value="">
      </div>

      <div id="modif" align="right" >
        <button onclick="insertBook()">Добавить</button>
      </div>
  `);
}

function checkInput() {

    var result = true;

    if ($("#BookTitle").val() == "") {
        alert('Пожалуйста, введите название книги!');
        result = false;
    } else
    if ($("#Author").val() == "") {
        alert('Пожалуйста, введите автора книги!');
        result = false;
    } else
    if ($("#Genre").val() == "") {
        alert('Пожалуйста, введите жанр книги!');
        result = false;
    }

    return result;
}

function insertBook(){

    if (checkInput() == false) {
        return
    }

    var newBook = {
            title: $("#BookTitle").val(),
            author: {
                name: $("#Author").val(),
            },
            genre: {
                name: $("#Genre").val()
            }
        }

    $.ajax({
        url: "/books",
        type: "POST",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(newBook)
    }).done(function() {
        pageLoader();
    });
}

function updateBook(bookID){

    if (checkInput() == false) {
        return
    }

    var book = {
            bookID: bookID,
            title: $("#BookTitle").val(),
            author: {
                name: $("#Author").val(),
            },
            genre: {
                name: $("#Genre").val()
            }
        }

    $.ajax({
        url: "/books",
        type: "PUT",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(book)
    }).done(function() {
        pageLoader();
    });

}

function deleteBook(bookID){

    $.ajax({
        url: "/books/"+bookID,
        type: "DELETE"
    }).done(function() {
        pageLoader();
    });

}
