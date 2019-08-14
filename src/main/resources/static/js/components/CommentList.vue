<template>
    <v-layout align-space-around justify-start column>
            <comment-card v-for="comment in this.sortComments"
                      :key="comment.id"
                      :comment="comment"
                      :deleteComment="delComment"
                       class="xs2" />
    </v-layout>
</template>

<script>
    import CommentCard from 'components/CommentCard.vue'
    import {mapActions} from 'vuex'

    export default {
        props:['cur_book'],

        components: {
            CommentCard
        },
        data() {
            return {
                comments:null
            }
        },
        computed:{
            sortComments:function () {
                this.comments = this.cur_book.comments.sort((a,b)=> {
                    if(Date.parse(a.created)<Date.parse(b.created)) return 1;
                    else if(Date.parse(a.created)>Date.parse(b.created))  return -1;
                    else return 0;
                });
                return this.comments;
            }
        },

        methods:{
            ...mapActions({removeComment: 'deleteComment'}),
            delComment:function (comment) {
                let dto ={
                    comment_id:comment.id};
                this.removeComment({book:this.cur_book.id,dto:comment.id});
            }
        },

    }
</script>

<style>
</style>