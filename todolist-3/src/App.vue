<template>
  <div class="todoListBox">
    <div class="header-box">
      <div class="header-left">
        <div>+</div>
        <h2>Todo List</h2>
      </div>
      <div class="header-right">
        <button class="headerAllSelect" @click="handleAllSelect">全选</button>
        <button class="headerAdd" @click="handleAdd">添加</button>
      </div>
    </div>
    <div class="content">
      <div class="content-item"
           v-for="(item,index) in todoList" :key="item.id">
        <!-- 选中 , checkBox -->
        <div class="content-left" @click="handleSelect(index,item.id)">
          <span
              :style="item.isCheck ? 'opacity : 1' : 'opacity:0'"
          ></span>
        </div>
        <!--   input 输入框-->
        <input
            type="text"
            class="content-input"
            v-model="item.text"
            :disabled="item.isCheck"
            :class="item.isCheck ? 'line-through' : ''"
            @blur="handleBlur"
            ref="inputBox"
        >
        <!--  info -->
        <div class="content-right">
          <p>{{ item.time }}</p>
          <button @click="handleDelItem(index,item.id)">删除</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import dayjs from "dayjs"

export default {
  name: 'App',
  data() {
    return {
      todoList: []
    }
  },
  created() {
    let getList = JSON.parse(window.localStorage.getItem("listTodo1"))
    if (getList === null) {
      this.todoList = [{
        id: this.randomID(),
        text: "请点击上方的添加按钮添加事件",
        isSelected: false,
        time: dayjs(new Date).format('YY-MM-DD HH:mm')
      }]
    } else {
      this.todoList = getList
    }
  },
  methods: {
    // 添加功能
    handleAdd() {
      this.todoList.unshift({
        id: this.randomID(),
        isCheck: false, // 是否选中
        text: "", // input 框内容
        time: dayjs(new Date).format("YY-MM-DD HH:mm"), // 时间
      })

      const inputLength = this.todoList.length - 1
      // 获取焦点
      this.$nextTick(() => {
        this.$refs.inputBox[inputLength].focus()
      })


    },
    //删除功能
    handleDelItem(index, id) {
      // console.log(index);
      // console.log(id);
      if (this.todoList[index].id === id) {
        this.todoList.splice(index, 1)
        this.storage()

      }
    },
    // 选中功能
    handleSelect(index, id) {
      // console.log(index);
      // console.log(id);
      if (this.todoList[index].id === id) {
        this.todoList[index].isCheck = !this.todoList[index].isCheck
        this.storage()

      }
    },
    //全选功能
    handleAllSelect() {
      this.todoList.forEach(item => {
        // console.log(item);
        // if (item.isCheck) return

        item.isCheck = !item.isCheck
        this.storage()

      })
    },
    // 判断是否输入完成
    handleBlur() {
      this.storage()
    },

    // 生成随机ID
    randomID() {
      return Number(Math.random().toString().substr(2, 0) + Date.now()).toString(10)
    },
    // 本地存储
    storage() {
      window.localStorage.setItem('listTodo1', JSON.stringify(this.todoList))
    }
  }
}
</script>

<style lang="less">
button {
  padding: 5px 10px;
  border: none;
  border-radius: 4px;
  color: #fff;
  margin-left: 10px;
}

.todoListBox {
  width: 800px;
  height: 600px;
  background: #3C3E4F;
  border-radius: 10px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  box-sizing: border-box;
  color: #fff;

  .header-box {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: #ccc 1px solid;

    .header-left {
      display: flex;
      align-items: center;

      div {
        width: 50px;
        height: 50px;
        border-radius: 50%;
        background: #9999E6;
        font-size: 30px;
        text-align: center;
        margin-right: 15px;
      }
    }

    .header-right {
      .headerAllSelect {
        background: #C43F38;
      }

      .headerAdd {
        background: #70B870;

      }
    }
  }

  .content {
    margin-top: 20px;
    height: 450px;
    overflow-y: scroll;

    .content-item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      background: #6B6F7D;
      border-radius: 8px;
      padding: 10px 20px;
      box-sizing: border-box;
      margin-top: 20px;

      .content-left {
        width: 30px;
        height: 30px;
        border: 1px solid #ccc;
        border-radius: 50%;
        position: relative;

        span {
          display: inline-block;
          width: 20px;
          height: 20px;
          background: #9999E6;
          border-radius: 50%;
          position: absolute;
          top: 50%;
          left: 50%;
          transform: translate(-50%, -50%);
        }
      }


      .content-input {
        flex: 1;
        margin: 0 10px;
        outline: none;
        background: transparent;
        border: none;
        border-bottom: 1px solid #ccc;
        padding: 5px 10px;
        color: #fff;
      }

      .line-through {
        color: rgba(255, 255, 255, 0.3); // 文字颜色
        text-decoration: line-through rgba(255, 255, 255, 0.8); // 横线颜色
      }

      .content-right {
        display: flex;
        align-items: center;

        button {
          background: #C43F38;
        }
      }
    }
  }
}
</style>
