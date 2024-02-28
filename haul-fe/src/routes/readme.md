# Router 폴더 설명

프로젝트에 Route에 대한 설정을 진행합니다.

---

# **Project routing architecture**

**`/`**

**`/login`**

**`/signUp`**

**`/more/list`
`/more/terms`
`/more/terms/services`
`/more/terms/privacy`
`/more/terms/transportation`
`/more/terms/location`
`/more/user-info`
`/more/user-payments`**

**`/request/type`
`/request/date`
`/request/time`
`/request/source`
`/request/destination`
`/request/loadInfo`
`/request/guestInfo`
`/request/result`
`/request/purchase`
`/request/complete`**

**`/check/list`
`/check/guest`
`/check/detail/:id`**

## 사용 방법

```jsx
<Route path="/" element={<Splash />} />
```

원하는 url을 path에 넣으시고 element값에 원하는 페이지 컴포넌트를 입력시에 연결됩니다.

1. path

   Page에 해당하는 url 입력

2. element

   페이지 컴포넌트를 입력

# 참고 자료

[Home v6.22.0](https://reactrouter.com/en/main)
