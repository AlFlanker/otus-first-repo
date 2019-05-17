<template>
    <v-flex>
    <v-card class="my-2 mx-auto" height="auto">
        <v-card-text primary-title>
            <h3><i> "{{ book.title }}"</i></h3>
            <div>
                <h4>Авторы:</h4>
                <li class="px-2" v-for="author in book.authors">{{ author.name }} {{ author.lastname }}</li>
            </div>
        </v-card-text>
        <v-card-actions>
            <v-layout align-center justify-end >
                <v-btn flat color="orange"  @click="show = !show">Подробнее</v-btn>
                <v-spacer></v-spacer>
                <v-btn icon>
                    <v-icon>favorite_border</v-icon>
                </v-btn>
                <v-btn icon>
                    <v-icon>mode_comment</v-icon>
                </v-btn>
            </v-layout>
        </v-card-actions>
        <v-slide-y-transition>
            <v-card-text v-show="show">
                <div><i>Дата выхода: {{book.releaseDate}}</i></div>
                <div><i>Издательство: "{{book.edition}}"</i></div>
                <h4>Краткое содержвание:</h4>
                <i>{{book.description}}</i>
            </v-card-text>
        </v-slide-y-transition>
    </v-card>
    </v-flex>
</template>

<script>
    export default {
        props: ['book', 'books'],
        methods: {},
        data: () => ({
            show: false
        })
    }
</script>

<style>
</style>

