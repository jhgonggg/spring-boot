

var instance = axios.create({
	// 允许携带cookie
	withCredentials: true
});

// instance.interceptors.request.use(function (config) {
//
// 	return config
// }, function (error) {
// 	alert('网络错误')
//   	return Promise.reject(error)
// })



// 拦截回文
instance.interceptors.response.use(function (response) {
	return response;
}, function (error) {
	alert('网络错误');
  	return Promise.reject(error)
});


