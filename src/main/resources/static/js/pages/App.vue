<template>
    <v-app>
        <navigation-drawer :genresNames="genresNames" :drawerNav="drawerNav" :authors="authors"></navigation-drawer>
        <v-toolbar fixed app :clipped-left="clipped">
            <v-toolbar-side-icon @click=showNav></v-toolbar-side-icon>
            <v-toolbar-title>Библиотека</v-toolbar-title>
            <v-spacer></v-spacer>

            <v-tooltip bottom>
                <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
                <v-icon @click="showLibraryBooks" >library_books</v-icon>
            </v-btn>
                </template>
                <span>библиотека</span>
            </v-tooltip>

            <v-tooltip v-if="user && user.roles.includes('ADMIN')" bottom>
                <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
                <v-icon @click="showBookAddForm">library_add</v-icon>
            </v-btn>
                </template>
                <span>Добавить новую книгу</span>
            </v-tooltip>

            <v-tooltip v-if="user && user.roles.includes('ADMIN')" bottom>
                <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
                <v-icon @click="showAuthorAddForm">person_add</v-icon>
            </v-btn>
                </template>
                <span>Добавить нового автора</span>
            </v-tooltip>
            <div>
                {{user?user.username:''}}
            </div>
            <v-tooltip bottom v-if = "user">
                <template v-slot:activator="{ on }">
            <v-btn icon  v-on="on">
                <span>
                    <v-icon @click="logout">input</v-icon>
                </span>
            </v-btn>
                </template>
                <span>Выйти</span>
            </v-tooltip>
            <v-tooltip bottom v-else>
                <template v-slot:activator="{ on }">
                    <v-btn icon  v-on="on">
                <span>
                    <v-icon @click="login">input</v-icon>
                </span>
                    </v-btn>
                </template>
                <span>Войти</span>
            </v-tooltip>


        </v-toolbar>

        <v-content>
            <v-container>
                <div v-if="showComment">
                    <comment-form :cur_book="book"></comment-form>
                    <comment-list  :cur_book="book"/>
                </div>

            </v-container>

            <v-container fluid v-if="authForm">
                <v-layout  align-center justify-center row fill-height>
                    <login-form :registrate="regOrAuth"></login-form>
                </v-layout>
            </v-container>

            <v-container fluid>
                <books-list v-if="showLibrary" :editForm="editBook" :showComments="showComments"/>
                <v-layout  align-center justify-center row fill-height v-else-if="addAuthorForm">
                    <author-add-form></author-add-form>
                </v-layout>
                <v-layout align-center justify-center row fill-height v-else-if="addBookForm">
                    <book-add-form :book="book" :editCompl="editCompl"></book-add-form>
                </v-layout>
            </v-container>
        </v-content>
        <v-footer class="pa-3">
            <v-spacer></v-spacer>
            <div>&copy; {{ new Date().getFullYear() }}</div>
        </v-footer>
    </v-app>
</template>

<script>
    import BooksList from 'components/BooksList.vue'
    import NavigationDrawer from 'components/NavigationDrawer.vue'
    import BookAddForm from 'components/BookAddForm.vue'
    import AuthorAddForm from 'components/AuthorAddForm.vue'
    import CommentList from 'components/CommentList.vue'
    import CommentForm from 'components/CommentForm.vue'
    import LoginForm from 'components/LoginForm.vue'
    import {mapActions, mapState} from 'vuex'


    export default {
        components: {
             BooksList, NavigationDrawer, AuthorAddForm,BookAddForm,CommentList,CommentForm, LoginForm
        },
        data() {
            return {
                date: new Date().toISOString().substr(0, 7),
                menu: false,
                modal: false,
                showComment:false,
                selectedGenres: [],
                drawerNav: false,
                clipped: false,
                addAuthorForm: false,
                addBookForm: false,
                showLibrary:true,
                e7: [],
                book:null,
                comments:null,
                authForm:false,
                regOrAuth:false
            }
        },
        computed: {
            ...mapState(['books', 'genres', 'genresNames','authors','user'])
        },
        created() {
            this.loadBooksAction();
            this.loadGenresAction();
            this.loadAuthorsAction();
        },
        methods: {
            ...mapActions(['loadBooksAction', 'loadGenresAction','loadAuthorsAction','resetBookStatusAction','resetAuthorStatusAction']),
            showNav() {
                if(this.addBookForm || this.addAuthorForm || this.showComment || this.authForm){
                    this.drawerNav = false;
                    this.clipped = false;
                    return;
                }
                this.drawerNav = !this.drawerNav;
                this.clipped = !this.drawerNav;
            },
            showAuthorAddForm() {
                this.book=null;
                this.showComment= false;
                this.addBookForm = false;
                this.showLibrary = false;
                this.addAuthorForm = true;
                this.authForm = false;
            },
            showBookAddForm() {
                this.book=null;
                this.showComment= false;
                this.addBookForm = true;
                this.showLibrary = false;
                this.addAuthorForm = false;
                this.authForm = false;
            },
            showLibraryBooks(){
                this.book=null;
                this.showComment= false;
                this.addBookForm = false;
                this.showLibrary = true;
                this.addAuthorForm = false;
                this.authForm = false;
            },
            editBook:function (book) {
                this.book = book;
                this.showComment= false;
                this.addBookForm = true;
                this.addAuthorForm = false;
                this.showLibrary = false;
                this.authForm = false;
            },
            showComments:function(book){
                this.book = book;
                this.comments = book.comments;
                this.showComment= true;
                this.addBookForm = false;
                this.addAuthorForm = false;
                this.showLibrary = false;
                this.authForm = false;
            },
            editCompl:function () {
                this.showBookAddForm();
            },
            logout:function () {
                this.$http.post("/logout").then(value => location.reload(true))
            },
            login:function () {
                this.showComment= false;
                this.addBookForm = false;
                this.addAuthorForm = false;
                this.showLibrary = false;
                this.authForm = true;
            }
        },
        mounted() {
            this.$store.subscribe((mutation, state) => {
                switch(mutation.type) {
                    case 'updateBookArr':
                        if(state.bookStatus === true){
                            this.resetBookStatusAction();
                            this.showLibraryBooks();
                        }
                        break;
                    case 'updateAuthorsArr':
                        if(state.authorStatus === true){
                            this.resetAuthorStatusAction();
                            this.showLibraryBooks();
                        }
                }
            });
        }

    }
</script>

<style>

</style>