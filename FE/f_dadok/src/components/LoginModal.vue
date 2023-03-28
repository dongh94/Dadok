<template>
  <b-modal id="login-modal">
    <template #modal-header="{}">
      <h4>로그인</h4>
    </template>
    <form @submit.stop.prevent="loginOk">
      <b-form-group label="아이디" label-for="id-input" invalid-feedback="아이디를 입력해주세요">
        <b-form-input id="id-input" required></b-form-input>
        <b-form-checkbox class="mt-2" v-model="idsaveflag" value="true" unchecked-value="false"> 아이디 저장 </b-form-checkbox>
      </b-form-group>
      <b-form-group label="비밀번호" label-for="pw-input" invalid-feedback="비밀번호를 입력해주세요">
        <b-form-input id="pw-input" type="password" required></b-form-input>
      </b-form-group>
    </form>
    <template #modal-footer="{}">
      <!-- <b-button size="sm" pill variant="outline-warning" @click.prevent="loginWithKakao"> 카카오 </b-button> -->
      <b-button size="sm" pill variant="outline-primary" @click.prevent="loginOk"> 로그인 </b-button>
      <b-button size="sm" pill variant="outline-warning" v-b-modal.find-modal> 비밀번호찾기 </b-button>
    </template>
  </b-modal>
</template>

<script>
export default {};
</script>

<style></style>
