<template>
    <v-app>
        <navigation-drawer :genresNames="genresNames" :drawerNav="drawerNav" :authors="authors"></navigation-drawer>
        <v-toolbar fixed app :clipped-left="clipped">
            <v-toolbar-side-icon @click=showNav></v-toolbar-side-icon>
            <v-toolbar-title>Библиотека</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn icon>
                <v-icon @click="showLibraryBooks" title="библиотека">library_books</v-icon>
            </v-btn>
            <v-btn icon>
                <v-icon @click="showBookAddForm" title="Добавить новую книгу">library_add</v-icon>
            </v-btn>
            <v-btn icon>
                <v-icon @click="showAuthorAddForm" title="Добавить нового автора">person_add</v-icon>
            </v-btn>
        </v-toolbar>

        <v-content>
            <v-container>
                <div v-if="showComment">
                    <comment-form :cur_book="book"></comment-form>
                    <comment-list  :cur_book="book"/>
                </div>

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
    import {mapActions, mapState} from 'vuex'


    export default {
        components: {
             BooksList, NavigationDrawer, AuthorAddForm,BookAddForm,CommentList,CommentForm
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
                comments:null
            }
        },
        computed: {
            ...mapState(['books', 'genres', 'genresNames','authors'])
        },
        created() {
            this.loadBooksAction();
            this.loadGenresAction();
            this.loadAuthorsAction();
        },
        methods: {
            ...mapActions(['loadBooksAction', 'loadGenresAction','loadAuthorsAction','resetBookStatusAction','resetAuthorStatusAction']),
            showNav() {
                if(this.addBookForm || this.addAuthorForm || this.showComment){
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
            },
            showBookAddForm() {
                this.book=null;
                this.showComment= false;
                this.addBookForm = true;
                this.showLibrary = false;
                this.addAuthorForm = false;
            },
            showLibraryBooks(){
                this.book=null;
                this.showComment= false;
                this.addBookForm = false;
                this.showLibrary = true;
                this.addAuthorForm = false;
            },
            editBook:function (book) {
                this.book = book;
                this.showComment= false;
                this.addBookForm = true;
                this.addAuthorForm = false;
                this.showLibrary = false;
            },
            showComments:function(book){
                this.book = book;
                this.comments = book.comments;
                this.showComment= true;
                this.addBookForm = false;
                this.addAuthorForm = false;
                this.showLibrary = false;
            },
            editCompl:function () {
                this.showBookAddForm();
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