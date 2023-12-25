import React from 'react';
import Styles from "../Layout/WebComponents/Header/Header.module.scss";
import clsx from "clsx";

const ButtonLogout = ({onclick}) => {
    return (
        <div onClick={onclick}>

            <li className={clsx(Styles["register_login-item"])}>
                <button style={{
                    background: "red",
                    display: "flex",
                    gap: "10px",
                    justifyContent: "center",
                    alignItems: "center",
                    padding: "10px 15px",
                    borderRadius: "20px",}}>
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="12"
                        height="12"
                        fill="currentColor"
                        class="bi bi-unlock"
                        viewBox="0 0 16 16"
                    >
                        <path
                            d="M11 1a2 2 0 0 0-2 2v4a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V9a2 2 0 0 1 2-2h5V3a3 3 0 0 1 6 0v4a.5.5 0 0 1-1 0V3a2 2 0 0 0-2-2M3 8a1 1 0 0 0-1 1v5a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V9a1 1 0 0 0-1-1z"/>
                    </svg>
                    <span>Đăng Xuất</span>
                </button>
            </li>
        </div>
    );
};

export default ButtonLogout;