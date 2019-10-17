<template>
    <v-card style="width:30%;" class="	justify-content:center;">
        <v-card-title style="padding-top: 2em; padding-left: 2em">
            <div class="title font-weight-light">{{this.registrate?'Форма регистрации:':'Авторизация:'}}</div>
        </v-card-title>
        <v-form
                ref="form"
                v-model="valid"
                lazy-validation
                style="padding: 1em; "
        >
            <v-text-field
                    v-model="username"
                    :counter="20"
                    :rules="nameRules"
                    label="Имя"
                    name = "username"
                    required
            ></v-text-field>

            <v-text-field
                    v-model="password"
                    :counter="20"
                    :rules="passwordRules"
                    label="Пароль"
                    name = "password"
                    required
            ></v-text-field>

            <v-text-field
                    v-model="repeat_password"
                    :counter="20"
                    :rules="repeatRule"
                    label="Повторите пароль"
                    required
                    v-if ="registrate"
            ></v-text-field>
            <v-card-actions>
                <v-btn
                        block color="success"
                        :disabled="!valid"
                        @click="validate"
                >
                    {{this.registrate?'Зарегистрироваться':'Авторизироваться'}}

                </v-btn>

            </v-card-actions>
            <v-card-actions v-if="!registrate">
                <div>
                    <a @click="registrate = true">Регистрация</a>
                </div>
            </v-card-actions>

        </v-form>
    </v-card>
</template>

<script>

    export default {
        props:["registrate"],
        data() {
            return {
                valid: false,
                password:'',
                username:'',
                repeat_password:'',

                nameRules: [
                    v => !!v || 'Имя пользователя не может быть пустым',
                    v => ( v && v.length <= 20 && v.length >= 4  ) || 'Имя пользователя может содержать от 4 до 20 символов'
                ],
                passwordRules:[
                    v => !!v || 'Пароль не может быть пустым',
                    v => ( v && v.length <= 20 && v.length >= 2  ) || 'Пароль может содержать от 4 до 20 символов'
                ],
                repeatRule:[
                    v => (v === this.password) || 'Пароль не совпадает!'
                ]
            }
        },
        methods: {
            validate() {
                if (this.$refs.form.validate()) {
                    let fd = new FormData();
                    fd.append("username",this.username);
                    fd.append("password",this.password);
                    if(this.registrate){
                    this.$http.post("/registration",fd, {headers: {
                        'Content-Type': 'multipart/form-data'
                    }}).then(value => {
                        if(value.status === 201){
                            this.$refs.form.reset();
                            this.registrate = false;
                        }

                    })
                    }else {
                        this.$http.post("/login",fd, {headers: {
                                'Content-Type': 'multipart/form-data'
                            }}).then(value => {
                                if(value.status === 200) {
                                    location.reload(true);
                                } else if(value.status === 401){
                                    this.$refs.form.reset();
                                    this.$refs.form.validate();
                                }
                        });
                    }

                }
            },
        }
    }
</script>

<style>

</style>