INSERT INTO `syspermission` (`id`, `available`, `name`, `parentId`, `parentIds`, `permission`, `resourceType`, `url`) VALUES (1, '1', '用户管理', 0, '0/', 'userInfo:view', 'menu', 'userInfo/userList');
INSERT INTO `syspermission` (`id`, `available`, `name`, `parentId`, `parentIds`, `permission`, `resourceType`, `url`) VALUES (2, '1', '用户添加', 1, '0/1', 'userInfo:add', 'button', 'userInfo/userAdd');
INSERT INTO `syspermission` (`id`, `available`, `name`, `parentId`, `parentIds`, `permission`, `resourceType`, `url`) VALUES (3, '1', '用户删除', 1, '0/1', 'userInfo:del', 'button', 'userInfo/userDel');
