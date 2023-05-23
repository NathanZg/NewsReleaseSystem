<template>
  <div class="bloglist">
    <div class="blogs" v-for="news in prop.newsList">
      <article class="excerpt">
        <a :href="'/detail/' + news.id" target="_blank" class="focus">
          <!--data-src="../assets/img/wechat.jpg",-->
          <img :src="getImgSrc(news.id)" alt="1">
        </a>
        <!--标题-->
        <header>
          <a :href="'/detail/' + news.id" target="_blank">
            {{ news.title }}
          </a>
        </header>
        <!--时间和作者-->
        <p class="meta">
          <time><i class="icon iconfont icon-time"></i>{{ news.date }}</time>
          <span><i class="icon iconfont icon-icon-awsauthor"></i>{{ news.publisher }}</span>
          <span>{{ news.type }}</span>
        </p>
        <!--文章简介-->
        <p class="note">
          {{ news.data }}
        </p>
      </article>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { ref, reactive, watch } from 'vue'
const prop = defineProps({
  newsList: {
    type: Object,
    default: ref([])
  }
})
function getImgSrc(id: number) {
  if(id % 3 == 0) {
    return "https://api.ghser.com/random/fengjing.php"
  } else if(id % 3 == 1){
    return "https://api.vvhan.com/api/view"
  } else {
    return "https://api.biuioi.com/random-picture/index.php"
  }
}
</script>
<style scope lang="scss">
$font1: 12px;

.blogs {

  /*width:100%;*/
  /*height: 100px;*/
  .excerpt {
    border: 2px solid #eaeaea;
    cursor: move;
    /*position: absolute;*/
    -webkit-transition: all .5s ease-out;
    transition: all .5s ease-out;
    border-radius: 4px 4px 0 0;
    overflow: hidden;
    width: 100%;
    padding: 16px;
    margin: 0 0 10px 0;
    box-sizing: border-box;

    &:hover {
      border-color: #d98b34;
      -webkit-transform: translateY(-5px);
      -ms-transform: translateY(-5px);
      transform: translateY(-5px);
      -webkit-box-shadow: 0 0 10px #999;
      box-shadow: 0 0 10px #999;
      -webkit-transition: all .5s ease-out;
      transition: all .5s ease-out;
    }

    .focus {
      float: left;
      display: inline;
      padding: 0;
      width: 30%;
      margin: 0px 20px 0 0px;

      img {
        width: 100%;
        /*border:1px solid transparent;*/
      }
    }

    header {
      text-align: left;

      a {
        margin-left: 4px;
        font-size: 20px;
      }
    }

    .meta {
      font-size: $font1;
      margin: 6px 0;
      text-align: left;

      i {
        font-size: 10px;
        margin: 0 4px;
      }

      span:nth-child(3) {
        margin-left: 10px;
        color: #2b2b2b;
        background-color: #ececec;
        border-radius: 3px;
        padding: 0 2px;
      }
    }

    .note {
      /* 两行溢出显示省略号*/
      text-align: left;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 2;
      overflow: hidden;
    }

  }

}
</style>