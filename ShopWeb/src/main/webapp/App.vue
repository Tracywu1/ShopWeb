<template>
  <div>
<!--     <h1>{{message}}</h1>
     <element-view></element-view>

     员工管理
     <emp-view></emp-view> -->

    <router-view></router-view>

  </div>
</template>

<script>
 import EmpView from '../js/shopWeb/EmpView.vue'
export default {
  components: {

  },
  data() {
    return {
      message: "Hello vue222"
    }
  },
  methods: {
    
  }
}
</script>

<style>

</style>
