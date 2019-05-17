<template>
    <v-container fluid grid-list-md>
    <v-layout row wrap>
        <book-row v-for="book in books"
        :key="book.id"
        :book="book"
        :books="books" class="xs4" />
    </v-layout>
    </v-container>
</template>

<script>
    import BookRow from 'components/BookRow.vue'

    export default {
        props: ['books'],
        components: {
            BookRow
        },
        data() {
            return {
                book: null
            }
        },

    }
</script>

<style>
</style>
