<template>
    <v-card class="my-2">
        <v-card-title ><h4>Автор: {{ comment.username }}</h4></v-card-title>
        <v-container>
        <v-card-text>
            <v-textarea readonly box v-model="comment.comment">
            </v-textarea>
        </v-card-text>
        </v-container>
        <v-card-actions>
            <v-card-text><i>Дата: </i> {{comment.created}}</v-card-text>
            <v-spacer></v-spacer>
            <v-btn v-if="user && user.roles.includes('ADMIN')" icon @click="del" title="Удалить комментарий" small>
                <v-icon>delete</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        props: ['comment','deleteComment'],
        methods: {
            edit() {

            },
            del() {
                this.deleteComment(this.comment);
            }
        }, computed: {
            ...mapState(['user']),
        },
    }
</script>

<style>
</style>