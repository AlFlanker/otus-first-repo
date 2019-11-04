<template>
    <v-layout align-space-around justify-start column>
        <div v-if="this.book.comments">
            <comment-card v-for="comment in this.sortComments"
                      :key="comment.id"
                      :comment="comment"
                      :deleteComment="delComment"
                       class="xs2" />
        </div>
    </v-layout>
</template>

<script>
    import CommentCard from 'components/CommentCard.vue'
    import {mapActions, mapState} from 'vuex'

    export default {
        components: {
            CommentCard
        },
        data() {
            return {
                comments:null
            }
        },
        computed:{
            ...mapState( ['book']),
            sortComments:function () {
                this.comments = this.book.comments.sort((a,b)=> {
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
                    comment_id:comment.id
                };
                this.removeComment({book:this.cur_book.id,dto:comment.id});
            }
        },
        created() {
            this.$store.dispatch('loadBookByIdAction',{amount: this.$route.params.id});

        },
        mounted() {
            this.$store.subscribe((mutation, state) => {
                switch(mutation.type) {
                    case 'loadBookMutation':
                        this.comments = this.book.comments.sort((a,b)=> {
                            if(Date.parse(a.created) < Date.parse(b.created)) return 1;
                            else if(Date.parse(a.created) > Date.parse(b.created))  return -1;
                            else return 0;
                        });
                        break;
                }
            });
        }

    }
</script>

<style>
</style>