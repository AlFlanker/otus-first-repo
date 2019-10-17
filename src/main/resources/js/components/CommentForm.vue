<template>
    <v-card
            class="mx-auto"
    >
        <v-card-title>
            <h3>Форма комметария</h3>
        </v-card-title>
        <v-form
           ref="form"
           v-model="form"
        >
            <v-container>
            <v-text-field
                    v-model="username"
                    :rules="[rules.required, rules.length_min(4),rules.length_max(20)]"
                    box
                    color="deep-purple"
                    counter="20"
                    label="Username"
                    style="min-height: 96px"
                    type="text"
            ></v-text-field>
            <v-textarea
                    v-model="text"
                    auto-grow
                    box
                    counter="4096"
                    :rules="[rules.required,rules.length_max(4096)]"
                    color="deep-purple"
                    label="Текст комментария"
                    rows="1"
            ></v-textarea>
            </v-container>
        </v-form>
        <v-card-actions>
            <v-btn
                    flat
                    @click="add"
            >
                Добавить
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn
                    flat
                    @click="$refs.form.reset()"
            >
                Очистить
            </v-btn>
            </v-card-actions>
    </v-card>

</template>

<script>
    import {mapActions, mapState} from 'vuex'

    export default {
        props:['cur_book'],
        data() {
            return {
                form:false,
                username:'',
                text: '',
                rules:{
                    length_min: len => v => ((v || '').length >= len || `Минимальная длинна ${len} символа`),
                    length_max: len => v => ((v || '').length <= len || `Масксимальная длинна ${len} символа`),
                    required: v => !!v || 'Поле не может быть пустым'
                },
            }
        },
        computed: {
            ...mapState(['user']),
        },
        methods:{
            ...mapActions({addComment: 'addComment'}),
            add () {
                if (this.$refs.form.validate()) {

                    const dto ={
                        username:this.username,
                        comment:this.text
                    };
                    this.addComment({book:this.cur_book.id,dto:dto});
                }
            },

        }

    }
</script>

<style>
</style>