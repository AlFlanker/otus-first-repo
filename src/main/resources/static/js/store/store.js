import Vue from 'vue'
import Vuex from 'vuex'
import booksApi from 'api/books'
import genresApi from  'api/genres'
import authorsApi from  'api/authors'
import commentsApi from 'api/comments'
import filterApi from 'api/filter'
Vue.use(Vuex);

export default  new Vuex.Store({
    state:{
        books: null,
        genres: null,
        genresNames: [],
        authors:null,
        authorsByNameAndlastname:[],
        bookStatus:null,
        authorStatus:null
    },
    mutations:{
        loadBooksMutation (state,books){
            state.books = books;
        },
        loadGenresMutation(state,genres){
            state.genres = genres;
        },
        setGenresNames(state,genresName){
            state.genresNames = genresName;
        },
        setAuthorsByNameAndLastname(state,authors){
            state.authorsByNameAndlastname = authors;
        },
        loadAuthosMutation(state,authors){
            state.authors = authors;
        },
        updateAuthorsArr(state,author){
            state.authorStatus = true;
            let pos = state.authors.findIndex(elem=>elem.id === author.id);
            if(pos > -1 ) state.authors.splice(pos,1,author);
            else state.authors.push(author);
            state.authorsByNameAndlastname.push(author.name +" " + author.lastname);
        },
        updateBookArr(state,book){
            state.bookStatus = true;
            let pos =state.books.findIndex(value =>value.id === book.id );
            if( pos>0) state.books.splice(pos, 1,book);
            else state.books.push(book);
        },
        updateBooksComments(state,book){

            let pos =state.books.findIndex(value =>value.id === book.id );
            if( pos>0) {
               state.books[pos].comments=book.comments;
            }
        },
        deleteBooksComment(state,book){
            let pos =state.books.findIndex(value =>value.id === book.id );
            if( pos>0) {
                state.books[pos].comments=book.comments;
            }
        },
        resetBookStatus(state){
            state.bookStatus = false;
        },
        resetAuthorStatus(state){
            state.authorStatus = false;
        }
    },
    getters:{
        getAuthors: state => line => {
            return state.authors.filter(a=> {
                    return (a.name === line.split(' ')[0]) && (a.lastname === line.split(' ')[1]);
                }
            );
        }
    },
    actions:{
        async loadBooksAction({commit}) {
            const result = await booksApi.getAll();
            const data = await result.json();
            commit('loadBooksMutation', data);
        },
        async loadGenresAction({commit}){
            const result = await genresApi.getAll();
            const data = await result.json();
            commit('loadGenresMutation', data);
            let genresName = data.map(g=>g.genreName);
            commit('setGenresNames', genresName);
        },
        async loadAuthorsAction({commit}){
            const result = await authorsApi.getAll();
            const data = await result.json();
            commit("loadAuthosMutation",data);
            let res = data.map(author => {
                return author.name + ' ' + author.lastname;
            });
            commit('setAuthorsByNameAndLastname', res);
        },
        async addAuthor({commit},author){
            const result = await authorsApi.add(author);
            if(result.status === 201){
                const save = await result.json();
                commit('updateAuthorsArr',save);
            }
        },
        async addBook({commit},book){
            const result = await booksApi.add(book);
            if(result.status === 201){
                const save = await result.json();
                commit('updateBookArr',save);
            }
        },
        async updateBook({commit},book){
            const result = await booksApi.update(book);
            if(result.status === 200){
                const save = await result.json();
                commit('updateBookArr',save);
            }
        },
        async addComment({commit},data){

            const result = await commentsApi.add(data.book,data.dto);
            if(result.status === 201){
                const save = await result.json();
                commit('updateBooksComments',save);
            }
        },
        async deleteComment({commit},data){
            const result = await commentsApi.delete(data.book,data.dto);
            if(result.status === 200){
                const del = await result.json();
                commit('deleteBooksComment',del);
            }
        },
        async getBookByFilter({commit},filter){
            const result = await filterApi.getByParam(filter);
            if(result.status ===200){
                const data = await result.json();
                commit('loadBooksMutation', data);

            }
        },
        resetBookStatusAction({commit}){
            commit('resetBookStatus');
        },
        resetAuthorStatusAction({commit}){
            commit('resetAuthorStatus');
        }
    }
})