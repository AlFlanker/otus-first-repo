<template>
    <v-container fluid grid-list-md>
    <v-layout row wrap>
        <book-row v-for="book in this.books"
        :key="book.id"
        :book="book"
                  class="xs3 md4 sm6" />
    </v-layout>
    </v-container>
</template>

<script>
    import BookRow from 'components/BookCard.vue'
    import {mapState} from 'vuex'

    export default {
        components: {
            BookRow
        },
        data() {
            return {

            }
        },
        computed:{
            ...mapState(['books'])
        },
        methods:{
        }

    }
</script>

<style>
</style>
