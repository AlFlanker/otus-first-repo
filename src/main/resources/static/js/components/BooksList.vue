<template>
    <v-container fluid grid-list-md>
    <v-layout row wrap>
        <book-row v-for="book in this.books"
        :key="book.id"
        :book="book"
        :edit="editBook"
        :showComments="showCommentsFunc"
                  class="xs3 md4 sm6" />
    </v-layout>
    </v-container>
</template>

<script>
    import BookRow from 'components/BookCard.vue'
    import {mapState} from 'vuex'

    export default {
        props:['editForm','showComments'],
        components: {
            BookRow
        },
        data() {
            return {

            }
        },
        computed:{
            ...mapState(['books', 'genres','genresNames'])
        },
        methods:{
            editBook:function (book) {
                this.editForm(book);
            },
            showCommentsFunc:function (book) {
                this.showComments(book);
            }
        }

    }
</script>

<style>
</style>
