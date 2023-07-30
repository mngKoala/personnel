import request from '@/utils/request'


//////////////////////////////////////招聘岗位/////////////////////////////////////////////////
export function listRecruitPostRec(query) {
  return request({
    url: '/post/rec/list',
    method: 'get',
    params: query
  })
}

export function createRecruitPostRec(data) {
  return request({
    url: '/post/rec/create',
    method: 'post',
    data
  })
}

export function readminRecruitPostRec(data) {
  return request({
    url: '/post/rec/readmin',
    method: 'get',
    data
  })
}

export function updateRecruitPostRec(data) {
  return request({
    url: '/post/rec/update',
    method: 'post',
    data
  })
}

export function deleteRecruitPostRec(data) {
  return request({
    url: '/post/rec/delete',
    method: 'post',
    data
  })
}


//////////////////////////////////////招聘记录/////////////////////////////////////////////////
export function listRecruitPersonRec(query) {
  return request({
    url: '/person/rec/list',
    method: 'get',
    params: query
  })
}

export function createRecruitPersonRec(data) {
  return request({
    url: '/person/rec/create',
    method: 'post',
    data
  })
}

export function readminRecruitPersonRec(data) {
  return request({
    url: '/person/rec/readmin',
    method: 'get',
    data
  })
}

export function updateRecruitPersonRec(data) {
  return request({
    url: '/person/rec/update',
    method: 'post',
    data
  })
}

export function deleteRecruitPersonRec(data) {
  return request({
    url: '/post/rec/delete',
    method: 'post',
    data
  })
}