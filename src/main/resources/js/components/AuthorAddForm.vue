<template>
    <v-card style="width:30%;" class="justify-content:center;">
        <v-form ref="author_form" v-model="valid" style="padding: 30px; ">
            <h3>Форма добавления нового автора.</h3>
            <v-text-field v-model="name" :rules="nameRules" :counter="30" label="Имя" required>
            </v-text-field>
            <v-text-field v-model="lastname" :rules="lastnameRules" :counter="30" label="Фамилия" required>
            </v-text-field>
            <v-menu
                    ref="menu"
                    v-model="menu"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    lazy
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
            >
                <template v-slot:activator="{ on }">
                    <v-text-field
                            v-model="date"
                            label="Дата рождения"
                            prepend-icon="event"
                            readonly
                            v-on="on"
                    ></v-text-field>
                </template>
                <v-date-picker
                        ref="picker"
                        v-model="date"
                        :max="new Date().toISOString().substr(0, 10)"
                        :rules="[v => !!v || 'Необходимо указать дату']"
                        required
                        min="1000-01-01"
                        locale="Ru-ru"
                        @change="save"
                ></v-date-picker>
            </v-menu>
            <v-btn @click="addAuthor">Добавить</v-btn>
        </v-form>
    </v-card>
</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        data() {
            return {
                date: new Date().toISOString().substr(0, 10),
                menu: false,
                valid: false,
                name: '',
                lastname: '',
                nameRules: [
                    v => !!v || 'Имя не может быть пустой',
                    v => v.length <= 50 && v.length >= 2 || 'Не может быть более 50 символов и не менее 2'
                ],
                lastnameRules: [
                    v => !!v || 'Фамилия не может быть пустой',
                    v => v.length <= 50 && v.length >= 2 || 'Не может быть более 50 символов и не менее 2'
                ]
            }
        },
        computed: {
            computedDateFormatted() {
                return this.formatDate(this.date)
            }

        },
        watch: {
            menu(val) {
                val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
            }
        },
        methods: {

            ...mapActions({saveAuthor: 'addAuthor'}),
            addAuthor() {
                if (this.$refs.author_form.validate()) {
                    let author = {
                        name: this.name,
                        lastname: this.lastname,
                        dateOfBirth: this.date
                    };
                    this.saveAuthor(author);
                }
            },
            save(date) {
                this.$refs.menu.save(date)
            }

        }
    }
</script>

<style>

</style>