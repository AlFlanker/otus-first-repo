<template>
    <v-flex>
    <v-card class="my-2 mx-auto" min-height="300px" height="auto">

        <v-card-text primary-title>
            <h3><i> "{{ book.title }}"</i></h3>
            <v-container>
            <div>
                <h4>Авторы:</h4>
                <li class="px-2" v-for="author in book.authors">{{ author.name }} {{ author.lastname }}</li>
                <div><i>Дата выхода: {{book.releaseDate}}</i></div>
                <div><i>Издательство: "{{book.edition}}"</i></div>
            </div>
            <div>
                <h4>Жанр:</h4>
                <li class="px-2" v-for="genre in book.genres">{{genre.genreName}}</li>
            </div>
            </v-container>
        </v-card-text>
        <v-card-actions>
            <v-layout align-end justify-center fill-height >
                <v-btn flat color="orange"  @click="show = !show">Подробнее</v-btn>
                <v-spacer></v-spacer>
                <v-btn icon v-if="user && user.roles.includes('ADMIN')">
                    <v-icon @click="editBook" title="Редактировать книгу" >edit</v-icon>
                </v-btn>

                <v-btn icon>
                    <v-icon @click="showCommentsFunc" title="Комментарии">mode_comment</v-icon>
                </v-btn>
            </v-layout>
        </v-card-actions>
        <v-slide-y-transition>
            <v-card-text v-show="show">
                <h4>Краткое содержвание:</h4>
                <i>{{book.description}}</i>
            </v-card-text>
        </v-slide-y-transition>
    </v-card>
    </v-flex>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        props: ['book','edit','showComments'],
        methods: {
            editBook:function () {
                this.edit(this.book);
            },
            showCommentsFunc:function () {

;                this.showComments(this.book);
            }
        },
        data: () => ({
            show: false
        }), computed: {
            ...mapState(['user']),
        }
    }
</script>

<style>
</style>

